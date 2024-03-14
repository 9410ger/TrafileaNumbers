package com.trafilea.numbers.trafileanumbers.service.impl

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import com.trafilea.numbers.trafileanumbers.exception.NumberTrafileaNotFoundException
import com.trafilea.numbers.trafileanumbers.repository.usecase.NumberTrafileaRepository
import com.trafilea.numbers.trafileanumbers.service.usecase.NumberTrafileaServiceImpl
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
import java.util.Optional

class NumberTrafileaServiceImplTest {

    @Mock
    private lateinit var numberTrafileaRepository: NumberTrafileaRepository

    @InjectMocks
    private lateinit var numberTrafileaServiceImpl: NumberTrafileaServiceImpl

    private val type1 = "Type 1"
    private val type2 = "Type 2"
    private val type3 = "Type 3"

    @BeforeEach
    fun setUp(){
        openMocks(this)
    }

    @Test
    fun saveShouldSaveNumberType1(){
        val number = 18L
        val numberTrafilea = NumberTrafilea(number, type1)
        `when`(numberTrafileaRepository.save(numberTrafilea))
            .thenReturn(numberTrafilea)

        `when`(numberTrafileaRepository.getByNumber(number))
            .thenThrow(NumberTrafileaNotFoundException("Number $number not found"))

        val result = numberTrafileaServiceImpl.save(number)

        verify(numberTrafileaRepository, times(1))
            .save(numberTrafilea)

        Assertions.assertEquals(type1, result.type)
    }

    @Test
    fun saveShouldSaveNumberType2(){
        val number = 25L
        val numberTrafilea = NumberTrafilea(number, type2)

        `when`(numberTrafileaRepository.getByNumber(number))
            .thenThrow(NumberTrafileaNotFoundException("Number $number not found"))

        `when`(numberTrafileaRepository.save(numberTrafilea))
            .thenReturn(numberTrafilea)

        val result = numberTrafileaServiceImpl.save(number)

        verify(numberTrafileaRepository, times(1))
            .save(numberTrafilea)

        Assertions.assertEquals(type2, result.type)
    }
    @Test
    fun saveShouldSaveNumberType3(){
        val number = 15L
        val numberTrafilea = NumberTrafilea(number, type3)
        `when`(numberTrafileaRepository.save(numberTrafilea))
            .thenReturn(numberTrafilea)

        `when`(numberTrafileaRepository.getByNumber(number))
            .thenThrow(NumberTrafileaNotFoundException("Number $number not found"))

        val result = numberTrafileaServiceImpl.save(number)

        verify(numberTrafileaRepository, times(1))
            .save(numberTrafilea)

        Assertions.assertEquals(type3, result.type)
    }

    @Test
    fun saveShouldSaveNumberWithSameNumber(){
        val number = 34L
        val numberTrafilea = NumberTrafilea(number, number.toString())
        `when`(numberTrafileaRepository.save(numberTrafilea))
            .thenReturn(numberTrafilea)

        `when`(numberTrafileaRepository.getByNumber(number))
            .thenThrow(NumberTrafileaNotFoundException("Number $number not found"))

        val result = numberTrafileaServiceImpl.save(number)

        verify(numberTrafileaRepository, times(1))
            .save(numberTrafilea)

        Assertions.assertEquals(number.toString(), result.type)
    }

    @Test
    fun saveNumberAlreadyExistsShouldNotSavingAgain(){
        val number = 34L
        val numberTrafilea = NumberTrafilea(number, number.toString())

        `when`(numberTrafileaRepository.getByNumber(number))
            .thenReturn(Optional.of(numberTrafilea))

        val result = numberTrafileaServiceImpl.save(number)

        verify(numberTrafileaRepository, times(0))
            .save(numberTrafilea)

        Assertions.assertEquals(number.toString(), result.type)
    }

    @Test
    fun getByNumberShouldGetNumberTrafilea(){
        val number = 15L
        val numberTrafilea = NumberTrafilea(number, type3)

        `when`(numberTrafileaRepository.getByNumber(number))
            .thenReturn(Optional.of(numberTrafilea))

        val result = numberTrafileaServiceImpl.getByNumber(number)

        verify(numberTrafileaRepository, times(1))
            .getByNumber(number)

        Assertions.assertEquals(type3, result.type)

    }

    @Test
    fun getByNumberShouldThrowNotFoundNumberException(){
        val number = 15L

        `when`(numberTrafileaRepository.getByNumber(number))
            .thenThrow(NumberTrafileaNotFoundException("Number $number not found"))

        assertThrows<NumberTrafileaNotFoundException> {
            numberTrafileaServiceImpl.getByNumber(number)
        }

        verify(numberTrafileaRepository, times(1))
            .getByNumber(number)
    }

    @Test
    fun getAllShouldReturnAllNumberTrafilea(){
        val number = 15L
        val number1 = 32L
        val numberTrafilea = NumberTrafilea(number, type3)
        val numberTrafilea1 = NumberTrafilea(number1, number1.toString())
        val numberList = listOf(numberTrafilea, numberTrafilea1)

        `when`(numberTrafileaRepository.getAll())
            .thenReturn(numberList)

        val result = numberTrafileaServiceImpl.getAll()

        verify(numberTrafileaRepository, times(1))
            .getAll()

        Assertions.assertEquals(2, result.size)
    }

}