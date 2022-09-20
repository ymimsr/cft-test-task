package com.ymimsr.cfttesttask.domain.usecase

import com.ymimsr.cfttesttask.domain.model.User
import com.ymimsr.cfttesttask.domain.repository.UserRepository

class SignUpUseCase(private val userRepository: UserRepository) {

    fun execute(user: User) {
        userRepository.save(user)
    }

}