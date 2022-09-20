package com.ymimsr.cfttesttask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.ymimsr.cfttesttask.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var vm: SignUpViewModel

    private lateinit var firstNameField: EditText
    private lateinit var firstNameFieldLayout: TextInputLayout
    private lateinit var lastNameField: EditText
    private lateinit var lastNameFieldLayout: TextInputLayout
    private lateinit var birthdayField: EditText
    private lateinit var birthdayFieldLayout: TextInputLayout
    private lateinit var passwordField: EditText
    private lateinit var passwordFieldLayout: TextInputLayout
    private lateinit var confirmPasswordField: EditText
    private lateinit var confirmPasswordFieldLayout: TextInputLayout
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        vm = ViewModelProvider(this, SignUpViewModelFactory(this))
            .get(SignUpViewModel::class.java)

        firstNameField = findViewById(R.id.firstName)
        firstNameFieldLayout = findViewById(R.id.firstNameLayout)
        lastNameField = findViewById(R.id.lastName)
        lastNameFieldLayout = findViewById(R.id.lastNameLayout)
        birthdayField = findViewById(R.id.birthday)
        birthdayFieldLayout = findViewById(R.id.birthdayLayout)
        passwordField = findViewById(R.id.password)
        passwordFieldLayout = findViewById(R.id.passwordLayout)
        confirmPasswordField = findViewById(R.id.confirmPassword)
        confirmPasswordFieldLayout = findViewById(R.id.confirmPasswordLayout)
        signUpButton = findViewById(R.id.signUp)

        vm.firstNameLengthErrorMessage.observe(this) {
            //firstNameFieldLayout.isEndIconVisible = !it.isNullOrEmpty()
            firstNameFieldLayout.error = it
        }

        vm.lastNameLengthErrorMessage.observe(this) {
            //lastNameFieldLayout.isEndIconVisible = !it.isNullOrEmpty()
            lastNameFieldLayout.error = it
        }

        vm.birthdayErrorMessage.observe(this) {
            //birthdayFieldLayout.isEndIconVisible = !it.isNullOrEmpty()
            birthdayFieldLayout.error = it
        }

        vm.passwordErrorMessage.observe(this) {
            //passwordFieldLayout.isEndIconVisible = !it.isNullOrEmpty()
            passwordFieldLayout.error = it
        }

        vm.passwordMismatchErrorMessage.observe(this) {
            //confirmPasswordFieldLayout.isEndIconVisible = !it.isNullOrEmpty()
            confirmPasswordField.error = it
        }

        vm.isValid.observe(this) {
            signUpButton.isEnabled = it
        }

        firstNameField.doAfterTextChanged {
            vm.onFirstNameFieldChange(firstNameField.text.toString())
        }

        lastNameField.doAfterTextChanged {
            vm.onLastNameFieldChange(lastNameField.text.toString())
        }

        passwordField.doAfterTextChanged {
            vm.onPasswordFieldChange(passwordField.text.toString())
        }

        confirmPasswordField.doAfterTextChanged {
            vm.onConfirmPasswordFieldChange(
                passwordField.text.toString(),
                confirmPasswordField.text.toString()
            )
        }

        val materialDateBuilder = MaterialDatePicker.Builder.datePicker()
        materialDateBuilder.setTitleText(getString(R.string.title_date_picker))
        val materialDatePicker = materialDateBuilder.build()

        birthdayField.setOnClickListener {
            materialDatePicker.show(supportFragmentManager, "MATERIAL_DATE_PICKER")
        }

        materialDatePicker.addOnPositiveButtonClickListener {
            birthdayField.setText(materialDatePicker.headerText)
            vm.onBirthdayFieldChange(birthdayField.text.toString())
        }

        signUpButton.setOnClickListener {
            vm.onSignUpButtonClick(
                firstName = firstNameField.text.toString(),
                lastName = lastNameField.text.toString(),
                birthday = birthdayField.text.toString(),
                password = passwordField.text.toString()
            )
        }

    }

}