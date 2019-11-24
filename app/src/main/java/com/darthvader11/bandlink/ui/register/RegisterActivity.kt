package com.darthvader11.bandlink.ui.register

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.darthvader11.bandlink.R
import com.darthvader11.bandlink.ui.login.afterTextChanged
import java.util.Observer

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        this.window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val username = findViewById<EditText>(R.id.username_register)
        val email = findViewById<EditText>(R.id.email_register)
        val password = findViewById<EditText>(R.id.password_register)
        val verifyPassword = findViewById<EditText>(R.id.verify_password)
        val registerBtn = findViewById<Button>(R.id.registerBtn)

        registerViewModel = ViewModelProviders.of(this, RegisterViewModelFactory()).get(RegisterViewModel::class.java)

        registerViewModel.registerFormState.observe(this@RegisterActivity, androidx.lifecycle.Observer {
            val registerState: RegisterFormState = it ?: return@Observer

            registerBtn.isEnabled = registerState.isDataValid

            if(registerState.usernameError != null){
                username.error = getString(registerState.usernameError)
            }
            if(registerState.mailError != null){
                email.error = getString(registerState.mailError)
            }
            if(registerState.passwordError != null){
                password.error = getString(registerState.passwordError)
            }
            if(registerState.verifyPasswordError != null){
                verifyPassword.error = getString(registerState.verifyPasswordError)
            }

        })

        username.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                email.text.toString(),
                password.text.toString(),
                verifyPassword.text.toString()
            )
        }

        email.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                email.text.toString(),
                password.text.toString(),
                verifyPassword.text.toString()
            )
        }

        password.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                email.text.toString(),
                password.text.toString(),
                verifyPassword.text.toString()
            )
        }

        verifyPassword.afterTextChanged {
            registerViewModel.registerDataChanged(
                username.text.toString(),
                email.text.toString(),
                password.text.toString(),
                verifyPassword.text.toString()
            )
        }

    }
}