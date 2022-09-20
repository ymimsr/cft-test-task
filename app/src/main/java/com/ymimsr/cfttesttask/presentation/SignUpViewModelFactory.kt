package com.ymimsr.cfttesttask.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ymimsr.cfttesttask.data.repository.UserRepositoryImpl
import com.ymimsr.cfttesttask.data.storage.sharedpref.SharedPrefUserStorage
import com.ymimsr.cfttesttask.domain.usecase.SignUpUseCase

class SignUpViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userRepository by lazy {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }

    private val signUpUseCase by lazy {
        SignUpUseCase(userRepository = userRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel(
            signUpUseCase
        ) as T
    }

}