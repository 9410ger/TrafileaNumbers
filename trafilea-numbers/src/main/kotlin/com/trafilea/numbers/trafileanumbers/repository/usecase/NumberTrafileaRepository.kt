package com.trafilea.numbers.trafileanumbers.repository.usecase

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import java.util.Optional

interface NumberTrafileaRepository {

    fun save(numberTrafilea: NumberTrafilea): NumberTrafilea

    fun getByNumber(number: Long): Optional<NumberTrafilea>

    fun getAll(): List<NumberTrafilea>

}