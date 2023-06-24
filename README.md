### Пример реализации сервиса Spring Boot (Kotlin, читсая архитектура, многомодульность)

#### Описание предметной области
Есть записи, хранящие информацию о потоках интернет вещания (radio stream). 
Пример такого потока: http://icecast.vgtrk.cdnvideo.ru/vestifm_mp3_192kbps .
Запись имеет разные свойства:
1) язык вещания (русский, английский и так далее). В терминах базы данных: один - ко многим со
   стороны радиостанции и один ко многим со стороны "язык вещания".
2) Также запись имеет список своих потоков с разным уровнем битрейтов(более высокий битрейт
   соответсвует более качественному потоку). Список потоков - внутреннее свойство RadioStream. В терминах базы данных:
   один - ко многим со стороны радиостанции.
3) Также имеется свойство "категория или жанр стоанции": "разговорный", "поп", "рок", "юмор" и так далее.  Жанры и "станции"
   имеют отншение друг к другу как "многие ко многим".


#### Описание модулей ("common", "storage", "app")
В данном примере: 
1) "common" - модуль ядра с моделями. Этот модуль в центре "луковицы". В модуле не должно быть никаких специфических 
   зависистей работы с СУБД, сетевыми библиотеками и так далее.
   Аналог "domain" "чистой архитектуры". Здесь чистый Kotlin / Java. Здесь также все константы приложения.
2) "storage" - модуль, реализующий логику хранения. В данном примере - в базе данных, используя JPA. Здесь 
   имплементация сервисов хранения, объявленных в "common" О данном модуле далее чуть подробнее.
3) "app" - стартовая точка приложения, которая собирает все зависимости в рабочее приложение.
   Здесь общие настройки приложения по безопасности, RestController's и другие элементы
   на границе "чистой архитектуры".

   
### Пояснения  
Реализована многомодульная "чистая" архитектура.
Первоисточник: "The Clean Code Blog by Robert C. Martin (Uncle Bob)" [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

![The Clean Architecture](./resource/CleanArchitecture.jpg)


Некоторые разработчики относят классы Entity JPA к категории моделей что в корне неверно. 
Entity у Роберта Мартина "не равны" Entity JPA. Не смешиваем модели и реализации.

Пример модели:
```kotlin
    data class StationType(@Size(max = 255) val name: String)
```
Пример DTO:
```kotlin
    data class StationTypeDTO(val id: Long, val stationType: StationType)
```
Пример описания класса для реализация хранения в БД:
```kotlin
@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "station_type")
class StationTypeDb : Keyed<Long> {

   @Id
   @Column(
      nullable = false,
      updatable = false
   )
   @SequenceGenerator(
      name = "primary_sequence",
      sequenceName = "primary_sequence",
      allocationSize = 1,
      initialValue = 1
   )
   @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "primary_sequence"
   )
   var id: Long? = null

   @Column(
      nullable = false,
      unique = true
   )
   var name: String? = null

   @ManyToMany
   @JoinTable(
      name = "radio_types",
      joinColumns = [JoinColumn(name = "station_type_id")],
      inverseJoinColumns = [JoinColumn(name = "station_id")]
   )
   var stations: MutableSet<StationDb>? = null

   override fun getKey(): Long = id ?: 0

   override fun setKey(key: Long): Boolean {
      id = key
      return true
   }
}
```
Реализация DTO может быть разной. В данной реализации формула для DTO следующая: "ИД-обекта в БД" + "объект модели".
Вторая распространенная формула как вариант: "ИД-обьекта в БД" + "поля из модели". Первая формула имеет преимущества 
при дальнейших маппингах из DTO в model:
Пример DTO:
```kotlin
    val childModel = obDTO.например_список_childDTO.map { it.childModel }
```

Для маппинга используется https://mapstruct.org/, которая интегрирована с Kotlin.

Остальные подробности - в коде.

![Happy_coding](./resource/Happy_coding.png)


### Аctuator
* [actuator](http://localhost:8080/actuator)
* [app-info](http://localhost:8080/actuator/info)
* [health](http://localhost:8080/actuator/health)
* [swagger](http://localhost:8080/swagger-ui/index.html#/)

### Дополнительная информация

* [Creating a Gradle multi-module project](https://tmsvr.com/gradle-multi-module-build/)
* [Containerizing Maven/Gradle based Multi Module Spring Boot Microservices using Docker & Kubernetes](https://2much2learn.com/mavengradle-based-multi-module-spring-boot-microservices/)
* [Declaring Dependencies between Subprojects](https://docs.gradle.org/current/userguide/declaring_dependencies_between_subprojects.html)
* [Sharing Build Logic between Subprojects](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.4.RELEASE/gradle-plugin/reference/html/#managing-dependencies-using-in-isolation)
* [Creating a Multi Module Project](https://spring.io/guides/gs/multi-module/)
* [CORS with Spring](https://www.baeldung.com/spring-cors)
* [Fixing 401s with CORS Preflights and Spring Security](https://www.baeldung.com/spring-security-cors-preflight)
* [Enabling Cross Origin Requests for a RESTful Web Service](https://spring.io/guides/gs/rest-service-cors/)

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/gradle-plugin/reference/html/#build-image)
* [Coroutines section of the Spring Framework Documentation](https://docs.spring.io/spring/docs/6.0.9/spring-framework-reference/languages.html#coroutines)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#howto.data-initialization.migration-tool.liquibase)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#features.docker-compose)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#using.devtools)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#data.sql.r2dbc)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#actuator)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [R2DBC Homepage](https://r2dbc.io)

### Docker Compose support

This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* postgres: [`postgres:latest`](https://hub.docker.com/_/postgres)


