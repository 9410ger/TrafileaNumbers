package com.trafilea.numbers.trafileanumbers.repository.impl

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import com.trafilea.numbers.trafileanumbers.repository.usecase.NumberTrafileaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class NumberTrafileaRepositoryImpl: NumberTrafileaRepository {

    private var dataBase = HashMap<Long, String>()

    override fun save(numberTrafilea: NumberTrafilea): NumberTrafilea {
        if(!dataBase.containsKey(numberTrafilea.number)){
            dataBase[numberTrafilea.number] = numberTrafilea.type
        }
        return numberTrafilea
    }

    override fun getByNumber(number: Long): Optional<NumberTrafilea> {
        if(dataBase[number] != null){
            return Optional.of(NumberTrafilea(number, dataBase[number]!!))
        }
        return Optional.empty()
    }

    override fun getAll(): List<NumberTrafilea> {
        return dataBase.entries.map { NumberTrafilea(it.key, it.value) }
    }
}