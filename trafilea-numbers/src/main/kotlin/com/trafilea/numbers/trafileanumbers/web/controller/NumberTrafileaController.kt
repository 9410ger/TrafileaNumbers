package com.trafilea.numbers.trafileanumbers.web.controller

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import com.trafilea.numbers.trafileanumbers.service.impl.NumberTrafileaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/number-trafilea")
class NumberTrafileaController(
    private val numberTrafileaService: NumberTrafileaService
) {

    @PostMapping("/{number}")
    fun saveNumber(@PathVariable("number") number: Long): NumberTrafilea{
        return numberTrafileaService.save(number)
    }

    @GetMapping("/{number}")
    fun getByNumber(@PathVariable("number") number: Long): NumberTrafilea{
        return numberTrafileaService.getByNumber(number)
    }

    @GetMapping("/all")
    fun getAll(): List<NumberTrafilea>{
        return numberTrafileaService.getAll()
    }

}