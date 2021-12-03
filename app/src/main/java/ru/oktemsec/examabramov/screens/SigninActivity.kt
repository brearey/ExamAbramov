package ru.oktemsec.examabramov.screens

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ru.oktemsec.examabramov.R

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        //Инициализация виджетов
        val btnRegIn: Button = findViewById(R.id.btn_reg_in)
        val btnSignIn: Button = findViewById(R.id.btn_signin_in)
        val etEmailIn: EditText = findViewById(R.id.et_email_in)
        val etPassIn: EditText = findViewById(R.id.et_pass_in)

        //Переход в окно регистрации
        btnRegIn.setOnClickListener {
            val intent: Intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        //Проверка на пустые поля
        btnSignIn.setOnClickListener {
            if (etEmailIn.text.isEmpty() || etPassIn.text.isEmpty()) {
                ShowArlertDialogWindow("Есть пустые поля")
            }
            else if (!etEmailIn.text.contains("@", true) || !etEmailIn.text.contains(".", true) || etEmailIn.text.length < 6) {
                ShowArlertDialogWindow("Введите корректную электронную почту")
            }
            else {
                ShowArlertDialogWindow("Все норм")
                loginUser()
            }
        }
    }

    private fun loginUser() {

    }

    private fun ShowArlertDialogWindow(message: String) {
        let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("ok", DialogInterface.OnClickListener { dialog, i -> dialog.cancel() })
                setTitle("Внимание")
                setMessage(message)
            }
            builder.create()
        }?.show()
    }
}