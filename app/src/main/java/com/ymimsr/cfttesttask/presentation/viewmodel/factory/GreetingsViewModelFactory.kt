package com.ymimsr.cfttesttask.presentation.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ymimsr.cfttesttask.data.repository.UserRepositoryImpl
import com.ymimsr.cfttesttask.data.storage.sharedpref.SharedPrefUserStorage
import com.ymimsr.cfttesttask.domain.usecase.GreetingsUseCase
import com.ymimsr.cfttesttask.domain.usecase.IsUserPresentUseCase
import com.ymimsr.cfttesttask.presentation.viewmodel.GreetingsViewModel

class GreetingsViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userRepository by lazy {
        UserRepositoryImpl(userStorage = SharedPrefUserStorage(context = context))
    }

    private val greetingsUseCase by lazy {
        GreetingsUseCase(userRepository = userRepository)
    }

    private val isUserPresentUseCase by lazy {
        IsUserPresentUseCase(userRepository = userRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GreetingsViewModel(
            greetingsUseCase = greetingsUseCase,
            isUserPresentUseCase = isUserPresentUseCase
        ) as T
    }

}