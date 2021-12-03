package ru.oktemsec.examabramov.screens

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.oktemsec.examabramov.R
import ru.oktemsec.examabramov.api.ApiClient
import ru.oktemsec.examabramov.models.LoginRequest
import ru.oktemsec.examabramov.models.LoginResponse

class SigninActivity : AppCompatActivity() {

    lateinit var btnRegIn:Button
    lateinit var btnSignIn:Button
    lateinit var etEmailIn:EditText
    lateinit var etPassIn:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        //Инициализация виджетов
        btnRegIn = findViewById(R.id.btn_reg_in)
        btnSignIn = findViewById(R.id.btn_signin_in)
        etEmailIn = findViewById(R.id.et_email_in)
        etPassIn = findViewById(R.id.et_pass_in)

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
        val loginRequest: LoginRequest = LoginRequest(etEmailIn.text.toString(), etPassIn.text.toString())
        val apiClient:ApiClient = ApiClient()
        val loginResponseCall: Call<LoginResponse> = apiClient.getLogin().loginUser(loginRequest)

        loginResponseCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                Log.d("brearey", "Response received: ${response.body()}")
                if (response.isSuccessful) {
                    val intent:Intent = Intent(this@SigninActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@SigninActivity, "Вы успешно вошли!", Toast.LENGTH_LONG).show()
                    finish()
                }
                else {
                    Toast.makeText(this@SigninActivity, "Что-то пошло не так", Toast.LENGTH_LONG).show()
                    Log.d("brearey", "Don't success: ${response.errorBody().toString()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("brearey", "Failed", t)
                Toast.makeText(this@SigninActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
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