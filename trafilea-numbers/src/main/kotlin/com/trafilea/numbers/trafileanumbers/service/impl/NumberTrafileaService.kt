package com.trafilea.numbers.trafileanumbers.service.impl

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea

interface NumberTrafileaService {

    fun save(number: Long): NumberTrafilea

    fun getByNumber(number: Long): NumberTrafilea

    fun getAll(): List<NumberTrafilea>

}