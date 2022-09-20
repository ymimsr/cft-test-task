package com.ymimsr.cfttesttask.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.ymimsr.cfttesttask.R
import com.ymimsr.cfttesttask.presentation.viewmodel.SignUpViewModel
import com.ymimsr.cfttesttask.presentation.viewmodel.factory.SignUpViewModelFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var viewModel: SignUpViewModel

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

        viewModel = ViewModelProvider(this, SignUpViewModelFactory(this))
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

        viewModel.firstNameLengthErrorMessage.observe(this) {
            firstNameFieldLayout.error = it
        }

        viewModel.lastNameLengthErrorMessage.observe(this) {
            lastNameFieldLayout.error = it
        }

        viewModel.birthdayErrorMessage.observe(this) {
            birthdayFieldLayout.error = it
        }

        viewModel.passwordErrorMessage.observe(this) {
            passwordFieldLayout.error = it
        }

        viewModel.passwordMismatchErrorMessage.observe(this) {
            confirmPasswordField.error = it
        }

        viewModel.isValid.observe(this) {
            signUpButton.isEnabled = it
        }

        firstNameField.doAfterTextChanged {
            viewModel.onFirstNameFieldChange(firstNameField.text.toString())
        }

        lastNameField.doAfterTextChanged {
            viewModel.onLastNameFieldChange(lastNameField.text.toString())
        }

        passwordField.doAfterTextChanged {
            viewModel.onPasswordFieldChange(passwordField.text.toString())
        }

        confirmPasswordField.doAfterTextChanged {
            viewModel.onConfirmPasswordFieldChange(
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
            viewModel.onBirthdayFieldChange(birthdayField.text.toString())
        }

        signUpButton.setOnClickListener {
            viewModel.onSignUpButtonClick(
                firstName = firstNameField.text.toString(),
                lastName = lastNameField.text.toString(),
                birthday = birthdayField.text.toString(),
                password = passwordField.text.toString()
            )

            val switchToGreetingsIntent = Intent(this, GreetingsActivity::class.java)
            startActivity(switchToGreetingsIntent)
        }

    }

}