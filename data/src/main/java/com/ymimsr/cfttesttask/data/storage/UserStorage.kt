package com.ymimsr.cfttesttask.data.storage

import com.ymimsr.cfttesttask.domain.model.User

interface UserStorage {

    fun save(user: User)

    fun get(): User

}