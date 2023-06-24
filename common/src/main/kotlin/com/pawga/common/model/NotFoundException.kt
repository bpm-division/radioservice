package com.pawga.common.model

import java.lang.RuntimeException

class NotFoundException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

}