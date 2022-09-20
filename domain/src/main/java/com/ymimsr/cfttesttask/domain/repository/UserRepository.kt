package com.ymimsr.cfttesttask.domain.repository

import com.ymimsr.cfttesttask.domain.model.User

interface UserRepository {

    fun save(user: User)

    fun get(): User

}