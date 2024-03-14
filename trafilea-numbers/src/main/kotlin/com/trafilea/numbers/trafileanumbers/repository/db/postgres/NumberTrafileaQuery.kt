package com.trafilea.numbers.trafileanumbers.repository.db.postgres

class NumberTrafileaQuery {

    companion object{

        const val NUMBER_ID_PARAM = "number_id"

        const val NUMBER_TYPE_PARAM = "number_type"

        const val SAVE_NUMBER_TRAFILEA_QUERY = """
            INSERT INTO trafilea_numbers
            (number_id, number_type)
            VALUES (?, ?)
        """

        const val GET_NUMBER_TRAFILEA_QUERY = """
            SELECT * FROM trafilea_numbers
            WHERE number_id = :numberId
        """

        const val GET_ALL_NUMBERS_TRAFILEA_QUERY = """
            SELECT * FROM trafilea_numbers
        """

    }

}