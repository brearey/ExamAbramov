package ru.oktemsec.examabramov.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import ru.oktemsec.examabramov.R

class LaunchScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)

        //Переход в окно входа
        Handler().postDelayed({
            val intent: Intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}