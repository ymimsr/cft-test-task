package com.ymimsr.cfttesttask.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ymimsr.cfttesttask.domain.model.Username
import com.ymimsr.cfttesttask.domain.usecase.GreetingsUseCase
import com.ymimsr.cfttesttask.domain.usecase.IsUserPresentUseCase

class GreetingsViewModel(
    private val greetingsUseCase: GreetingsUseCase,
    private val isUserPresentUseCase: IsUserPresentUseCase
    ) : ViewModel() {

    val usernameLiveData = MutableLiveData<Username>()
    val isUserPresent = MutableLiveData<Boolean>()

    fun onGreetingsButtonClick() {
        usernameLiveData.value = greetingsUseCase.execute()
    }

    fun checkUser() {
        isUserPresent.value = isUserPresentUseCase.execute()
    }

}