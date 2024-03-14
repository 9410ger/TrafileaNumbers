package com.trafilea.numbers.trafileanumbers.repository.impl

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import com.trafilea.numbers.trafileanumbers.repository.db.postgres.NumberTrafileaQuery
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class NumberTrafileaRepositoryImplTest {

    @Mock
    private lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    @Mock
    private lateinit var jdbcTemplate: JdbcTemplate

    @InjectMocks
    private lateinit var numberTrafileaRepositoryImpl: NumberTrafileaRepositoryImpl

    @BeforeEach
    fun setUp(){
        openMocks(this)
    }


    @Test
    fun saveShouldSaveNumberTrafilea(){
        val number = 3L
        val numberType = "Type 1"
        val numberTrafilea = NumberTrafilea(number, numberType)

        val result = numberTrafileaRepositoryImpl.save(numberTrafilea)

        verify(jdbcTemplate, times(1))
            .update(
            NumberTrafileaQuery.SAVE_NUMBER_TRAFILEA_QUERY,
            number,
            numberType)

        Assertions.assertEquals(numberType, result.type)

    }

    @Test
    fun getAllShouldGetAllNumbers(){

        numberTrafileaRepositoryImpl.getAll()

        verify(namedParameterJdbcTemplate, times(1))
            .query(
                eq(NumberTrafileaQuery.GET_ALL_NUMBERS_TRAFILEA_QUERY),
                any<RowMapper<Pair<String, Long>>>()
            )
    }

}