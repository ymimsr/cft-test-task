package com.ymimsr.cfttesttask.domain.usecase

import com.ymimsr.cfttesttask.domain.model.Username
import com.ymimsr.cfttesttask.domain.repository.UserRepository

class GreetingsUseCase(private val userRepository: UserRepository) {

    fun execute() : Username {
        val user = userRepository.get()!!
        return Username(user.firstName, user.lastName)
    }

}