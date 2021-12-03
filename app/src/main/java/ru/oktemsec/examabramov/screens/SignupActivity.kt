package ru.oktemsec.examabramov.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.oktemsec.examabramov.R

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Переход обратно в окно входа
        val btnIHave: Button = findViewById(R.id.btn_i_have)
        btnIHave.setOnClickListener {
            val intent: Intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
    }
}