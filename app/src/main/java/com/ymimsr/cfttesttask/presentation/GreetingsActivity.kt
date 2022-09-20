package com.ymimsr.cfttesttask.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ymimsr.cfttesttask.R
import com.ymimsr.cfttesttask.presentation.viewmodel.GreetingsViewModel
import com.ymimsr.cfttesttask.presentation.viewmodel.factory.GreetingsViewModelFactory

class GreetingsActivity : AppCompatActivity() {

    private lateinit var viewModel: GreetingsViewModel

    private lateinit var greetingsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greetings)

        viewModel = ViewModelProvider(this, GreetingsViewModelFactory(this))
            .get(GreetingsViewModel::class.java)

        viewModel.isUserPresent.observe(this) {
            if (!it) {
                val switchToSignUpIntent = Intent(this, SignUpActivity::class.java)
                startActivity(switchToSignUpIntent)
            }
        }

        viewModel.checkUser()

        greetingsButton = findViewById(R.id.greetings)

        greetingsButton.setOnClickListener {
            viewModel.onGreetingsButtonClick()
        }

        val dialog = MaterialAlertDialogBuilder(this,
            R.style.ThemeOverlay_AppCompat)
            .setPositiveButton(resources.getString(R.string.ok_greetings)) { dialog, _ ->
                dialog.cancel()
            }
            .setTitle(R.string.dialog_title_greetings)

        viewModel.usernameLiveData.observe(this) {
            dialog.setMessage(getString(R.string.dialog_message_greetings, it.toString()))
            dialog.show()
        }
    }

}