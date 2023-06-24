package com.pawga.radio.app.mapper

import com.pawga.common.model.RadioStatus
import com.pawga.radio_bootify.domain.StationBitrateDb
import com.pawga.storage.domain.Audit
import com.pawga.storage.domain.LocaleDb
import com.pawga.storage.domain.StationDb
import com.pawga.storage.domain.StationTypeDb
import com.pawga.storage.mapper.ApiMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

/**
 * Created by sivannikov on 23.06.2023 18:52
 */
@SpringBootTest
class StationTest {

    private val log = LoggerFactory.getLogger(StationTest::class.java)

    @Test
    fun mappingStation() {
        log.info("mapping test run...")

        //given
        val type1 = StationTypeDb()
        type1.id = 201
        type1.name = "Pop"
        val type2 = StationTypeDb()
        type2.id = 202
        type2.name = "Talk"

        val bitrate1 = StationBitrateDb()
        bitrate1.id = 201
        bitrate1.bitrate = 64
        bitrate1.stream = "11111"
        val bitrate2 = StationBitrateDb()
        bitrate2.id = 201
        bitrate2.bitrate = 128
        bitrate2.stream = "22222"

        val localeDb = LocaleDb()
        localeDb.id = 101
        localeDb.name = "en_EN"

        val audit = Audit()
        audit.createdOn = LocalDateTime.now()
        audit.updatedOn = LocalDateTime.now()

        val stationDb = StationDb()
        stationDb.id = 11
        stationDb.status = RadioStatus.WORK
        stationDb.localeDb = localeDb
        stationDb.types = mutableSetOf(type1, type2)
        stationDb.streams = mutableSetOf(bitrate1, bitrate2)
        stationDb.id = 1
        stationDb.name = "Коммерсант"
        stationDb.description = "Про бизнес"
        stationDb.image = "https://www.msk.ru/images/1.png"
        stationDb.audit = audit

        //when
        val stationDTOv1 = ApiMapper.INSTANCE.entityToDTOv1(stationDb)
        assertEquals(stationDTOv1.station.name, stationDb.name)

        val stationDTO = ApiMapper.INSTANCE.entityToDTO(stationDb)
        assertEquals(stationDTO.name, stationDb.name)

        //then
        val stationDb1 = ApiMapper.INSTANCE.dtoToEntity(stationDTOv1)
        assertEquals(stationDb1.name, stationDTOv1.station.name)
        val stationDb2 = ApiMapper.INSTANCE.dtoToEntity(stationDTO)
        assertEquals(stationDb2.name, stationDTO.name)
        log.info("stationDb2 updatedOn: ${stationDb2.audit?.updatedOn}")

        log.info("mapping test end...")
    }
}