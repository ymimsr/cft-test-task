package com.ymimsr.cfttesttask.domain.model

import java.time.LocalDate

data class User (
    val firstName: String,
    val lastName: String,
    val birthday: LocalDate,
    val password: String
)