package com.trafilea.numbers.trafileanumbers.controller

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import com.trafilea.numbers.trafileanumbers.exception.NumberTrafileaNotFoundException
import com.trafilea.numbers.trafileanumbers.service.impl.NumberTrafileaService
import com.trafilea.numbers.trafileanumbers.web.controller.NumberTrafileaController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks

class NumberTrafileaControllerTest {

    @Mock
    private lateinit var numberTrafileaService: NumberTrafileaService

    @InjectMocks
    private lateinit var numberTrafileaController: NumberTrafileaController

    private val type1 = "Type 1"
    private val type2 = "Type 2"
    private val type3 = "Type 3"

    @BeforeEach
    fun setUp(){
        openMocks(this)
    }

    @Test
    fun saveNumberShouldSaveNumber(){
        val number = 15L
        val numberTrafilea = NumberTrafilea(number, type3)

        `when`(numberTrafileaService.save(number))
            .thenReturn(numberTrafilea)

        val result = numberTrafileaController.saveNumber(number)

        verify(numberTrafileaService, times(1))
            .save(number)

        Assertions.assertEquals(type3, result.type)
    }

    @Test
    fun getByNumberShouldGetNumber(){
        val number = 15L
        val numberTrafilea = NumberTrafilea(number, type3)

        `when`(numberTrafileaService.getByNumber(number))
            .thenReturn(numberTrafilea)

        val result = numberTrafileaController.getByNumber(number)

        verify(numberTrafileaService, times(1))
            .getByNumber(number)

        Assertions.assertEquals(type3, result.type)
    }
    @Test
    fun getByNumberShouldNotFoundMessage(){
        val number = 15L

        `when`(numberTrafileaService.getByNumber(number))
            .thenThrow(NumberTrafileaNotFoundException("Number $number not found"))

        assertThrows<NumberTrafileaNotFoundException> {
            numberTrafileaController.getByNumber(number)
        }

        verify(numberTrafileaService, times(1))
            .getByNumber(number)

    }

    @Test
    fun getAllShouldReturnAllNumbers(){
        val number = 15L
        val number1 = 32L
        val numberTrafilea = NumberTrafilea(number, type3)
        val numberTrafilea1 = NumberTrafilea(number1, number1.toString())
        val numberList = listOf(numberTrafilea, numberTrafilea1)

        `when`(numberTrafileaService.getAll())
            .thenReturn(numberList)

        val result = numberTrafileaService.getAll()

        verify(numberTrafileaService, times(1))
            .getAll()

        Assertions.assertEquals(2, result.size)
    }


}