package com.trafilea.numbers.trafileanumbers.service.usecase

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import com.trafilea.numbers.trafileanumbers.exception.NumberTrafileaNotFoundException
import com.trafilea.numbers.trafileanumbers.repository.usecase.NumberTrafileaRepository
import com.trafilea.numbers.trafileanumbers.service.impl.NumberTrafileaService
import org.springframework.stereotype.Service

@Service
class NumberTrafileaServiceImpl(
    private val numberTrafileaRepository: NumberTrafileaRepository
): NumberTrafileaService {
    override fun save(number: Long): NumberTrafilea {
        return numberTrafileaRepository.save(getTypeNumber(number))
    }

    override fun getByNumber(number: Long): NumberTrafilea {
        val optionalNumberTrafilea = numberTrafileaRepository.getByNumber(number)
        if(optionalNumberTrafilea.isPresent){
            return optionalNumberTrafilea.get()
        }
        throw NumberTrafileaNotFoundException("Number $number not found")
    }

    override fun getAll(): List<NumberTrafilea> {
        return numberTrafileaRepository.getAll()
    }

    private fun getTypeNumber(number: Long): NumberTrafilea{
        var counter = 0
        if(number % 3L == 0L) counter+= 3

        if(number % 5L == 0L) counter+= 5

        val type: String = when (counter){
            3 -> "Type 1"
            5 -> "Type 2"
            8 -> "Type 3"
            else -> number.toString()
        }
        return NumberTrafilea(number, type)
    }
}