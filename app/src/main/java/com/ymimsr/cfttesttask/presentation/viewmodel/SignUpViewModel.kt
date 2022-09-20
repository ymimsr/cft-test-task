package com.ymimsr.cfttesttask.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ymimsr.cfttesttask.domain.model.User
import com.ymimsr.cfttesttask.domain.usecase.SignUpUseCase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val firstNameErrorText = "First name has to be at least two characters long"
private const val lastNameErrorText = "Last name has to be at least two characters long"
private const val birthdayErrorText = "You have to be at least 18 years old to sign up"
private const val passwordErrorText = "Password has to be at least 12 characters long and include at least one digit and one capital letter"
private const val passwordMismatchErrorText = "Passwords don't match"

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    val firstNameLengthErrorMessage = MutableLiveData<String?>()
    val lastNameLengthErrorMessage = MutableLiveData<String?>()
    val birthdayErrorMessage = MutableLiveData<String?>()
    val passwordErrorMessage = MutableLiveData<String?>()
    val passwordMismatchErrorMessage = MutableLiveData<String?>()
    val isValid = MutableLiveData<Boolean>()

    private var firstNameFieldFlag = false
    private var lastNameFieldFlag = false
    private var birthdayFieldFlag = false
    private var passwordFieldFlag = false
    private var confirmPasswordFieldFlag = false

    init {
        isValid.value = false
    }

    fun onSignUpButtonClick(firstName: String, lastName: String, birthday: String, password: String) {
        signUpUseCase.execute(
            User(firstName, lastName, LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MMM d, yyyy")), password)
        )
    }

    fun onFirstNameFieldChange(firstNameFieldText: String) {
        firstNameFieldFlag = firstNameFieldText.length >= 2
        if (firstNameFieldFlag)
            firstNameLengthErrorMessage.value = null
        else
            firstNameLengthErrorMessage.value = firstNameErrorText


        checkIsValid()
    }

    fun onLastNameFieldChange(lastNameFieldText: String) {
        lastNameFieldFlag = lastNameFieldText.length >= 2
        if (lastNameFieldFlag)
            lastNameLengthErrorMessage.value = null
        else
            lastNameLengthErrorMessage.value = lastNameErrorText

        checkIsValid()
    }

    fun onBirthdayFieldChange(birthdayFieldText: String) {
        val birthday = LocalDate.parse(birthdayFieldText, DateTimeFormatter.ofPattern("MMM d, yyyy"))
        birthdayFieldFlag = (LocalDate.now().year - birthday.year) >= 18
        if (birthdayFieldFlag)
            birthdayErrorMessage.value = null
        else
            birthdayErrorMessage.value = birthdayErrorText

        checkIsValid()
    }

    fun onPasswordFieldChange(passwordFieldText: String) {
        passwordFieldFlag = Regex("^(?=.*[0-9])(?=.*[A-Z]).{12,}$").matches(passwordFieldText)
        if (passwordFieldFlag)
            passwordErrorMessage.value = null
        else
            passwordErrorMessage.value = passwordErrorText

        checkIsValid()
    }

    fun onConfirmPasswordFieldChange(passwordFieldText: String, confirmPasswordFieldText: String) {
        confirmPasswordFieldFlag = passwordFieldText == confirmPasswordFieldText
        if (confirmPasswordFieldFlag)
            passwordMismatchErrorMessage.value = null
        else
            passwordMismatchErrorMessage.value = passwordMismatchErrorText

        checkIsValid()
    }

    private fun checkIsValid() {
        isValid.value = firstNameFieldFlag &&
                lastNameFieldFlag &&
                birthdayFieldFlag &&
                passwordFieldFlag &&
                confirmPasswordFieldFlag
    }

}