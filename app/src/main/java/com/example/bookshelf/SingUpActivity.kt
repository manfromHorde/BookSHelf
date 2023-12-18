package com.example.bookshelf

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SingUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        auth = FirebaseAuth.getInstance()

        val signUpEmail = findViewById<EditText>(R.id.signup_email)
        val signUpPassword = findViewById<EditText>(R.id.signup_password)
        val signUpButton = findViewById<Button>(R.id.signup_button)
        val loginRedirectText = findViewById<TextView>(R.id.loginRedirectText)

        signUpButton.setOnClickListener {
            val email = signUpEmail.text.toString()
            val password = signUpPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Регистрация выполнена успешно
                        Toast.makeText(
                            this, "Registration successful. Please log in.",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Переходим на страницу входа
                        val intent = Intent(this, LoginPage::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Ошибка регистрации
                        Toast.makeText(
                            this, "Registration failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        loginRedirectText.setOnClickListener {
            // Переход к странице входа
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
