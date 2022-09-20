package com.ymimsr.cfttesttask.data.repository

import com.ymimsr.cfttesttask.data.storage.UserStorage
import com.ymimsr.cfttesttask.domain.model.User
import com.ymimsr.cfttesttask.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun save(user: User) {
        userStorage.save(user)
    }

    override fun get(): User {
        return userStorage.get()
    }

}