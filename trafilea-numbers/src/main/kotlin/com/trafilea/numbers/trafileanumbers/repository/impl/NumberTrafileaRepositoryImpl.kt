package com.trafilea.numbers.trafileanumbers.repository.impl

import com.trafilea.numbers.trafileanumbers.entity.NumberTrafilea
import com.trafilea.numbers.trafileanumbers.repository.db.postgres.NumberTrafileaQuery
import com.trafilea.numbers.trafileanumbers.repository.usecase.NumberTrafileaRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class NumberTrafileaRepositoryImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
    private val jdbcTemplate: JdbcTemplate
): NumberTrafileaRepository {

    private val numberIdParam = "numberId"

    override fun save(numberTrafilea: NumberTrafilea): NumberTrafilea {
        jdbcTemplate.update(
            NumberTrafileaQuery.SAVE_NUMBER_TRAFILEA_QUERY,
            numberTrafilea.number,
            numberTrafilea.type
        )
        return numberTrafilea
    }

    override fun getByNumber(number: Long): Optional<NumberTrafilea> {
        val param = mapOf(numberIdParam to number)
        return try{
            val numberTrafileaResult = namedParameterJdbcTemplate.queryForObject(
                NumberTrafileaQuery.GET_NUMBER_TRAFILEA_QUERY, param
            ) { rs, _ ->
                val numberId = rs.getLong(NumberTrafileaQuery.NUMBER_ID_PARAM)
                val numberType = rs.getString(NumberTrafileaQuery.NUMBER_TYPE_PARAM)
                Optional.of(NumberTrafilea(numberId, numberType))
            }
            Optional.of(numberTrafileaResult!!.get())
        }catch (ex: EmptyResultDataAccessException){
            Optional.empty()
        }

    }

    override fun getAll(): List<NumberTrafilea> {
        val menuRowMapper = RowMapper<Pair<Long, String>> { rs,_ ->
            rs.getLong(NumberTrafileaQuery.NUMBER_ID_PARAM) to rs.getString(NumberTrafileaQuery.NUMBER_TYPE_PARAM)
        }
        return namedParameterJdbcTemplate.query(
            NumberTrafileaQuery.GET_ALL_NUMBERS_TRAFILEA_QUERY,
            menuRowMapper
        ).map { pair ->
            NumberTrafilea(pair.first, pair.second)
        }.toList()
    }


}