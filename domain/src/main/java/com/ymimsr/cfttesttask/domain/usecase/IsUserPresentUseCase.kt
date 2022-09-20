package com.ymimsr.cfttesttask.domain.usecase

import com.ymimsr.cfttesttask.domain.repository.UserRepository

class IsUserPresentUseCase(private val userRepository: UserRepository) {

    fun execute(): Boolean {
        return userRepository.get() != null
    }

}