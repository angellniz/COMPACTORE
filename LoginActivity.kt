package com.example.compactore1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginBtn = findViewById(R.id.loginBtn)

        val sharedPref: SharedPreferences =
            getSharedPreferences("UserData", MODE_PRIVATE)

        loginBtn.setOnClickListener {

            val username = usernameInput.text.toString()

            val editor = sharedPref.edit()
            editor.putString("username", username)
            editor.putBoolean("loggedIn", true)
            editor.apply()

            // go back to HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}