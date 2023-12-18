package com.example.bookshelf
//

import android.content.Intent
import android.net.Uri
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.bookshelf.ui.theme.BookSHelfTheme
import com.example.bookshelf.ui.theme.BooksApp


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
//
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Проверяем, вошел ли пользователь
        val currentUser: FirebaseUser? = auth.currentUser

        if (currentUser == null) {
            // Если пользователь не вошел, переходим на экран входа
            startActivity(Intent(this, LoginPage::class.java))
            finish()
            return
        }

        // Если пользователь вошел, отображаем интерфейс приложения
        setContent {
            BookSHelfTheme {
                BooksApp(
                    onBookClicked = {
                        ContextCompat.startActivities(
                            this,
                            arrayOf(Intent(Intent.ACTION_VIEW, Uri.parse(it.previewLink))),
                            null
                        )
                    }
                )
            }
        }
    }
}



