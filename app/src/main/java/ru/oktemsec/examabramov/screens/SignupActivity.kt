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
import ru.oktemsec.examabramov.models.RegisterRequest
import ru.oktemsec.examabramov.models.RegisterResponse

class SignupActivity : AppCompatActivity() {

    lateinit var btnSignUp: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etFirstName: EditText
    lateinit var etSecname: EditText
    lateinit var etRePass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Инициализация виджетов
        btnSignUp = findViewById(R.id.btn_signup_reg)
        etEmail = findViewById(R.id.et_email_reg)
        etPassword = findViewById(R.id.et_pass_reg)
        etFirstName = findViewById(R.id.et_first_name)
        etSecname = findViewById(R.id.et_second_name)
        etRePass = findViewById(R.id.et_repass_reg)

        //Переход обратно в окно входа
        val btnIHave: Button = findViewById(R.id.btn_i_have)
        btnIHave.setOnClickListener {
            val intent: Intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }

        //Проверка на пустые поля и регистрация
        btnSignUp.setOnClickListener {
            if (etEmail.text.isEmpty() ||
                etFirstName.text.isEmpty() ||
                etPassword.text.isEmpty() ||
                etRePass.text.isEmpty() ||
                etSecname.text.isEmpty()) {
                showArlertDialogWindow("Есть пустые поля")
            } else if (!etEmail.text.contains("@", true) || !etEmail.text.contains(".", true) || etEmail.text.length < 6) {
                showArlertDialogWindow("Введите корректную электронную почту")
            } else if (etPassword.text.toString() != etRePass.text.toString()) {
                showArlertDialogWindow("Пароли не совпадают")
            }
            else {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        val registerRequest: RegisterRequest = RegisterRequest(
            etEmail.text.toString(),
            etPassword.text.toString(),
            etFirstName.text.toString(),
            etSecname.text.toString()
        )
        val apiClient: ApiClient = ApiClient()
        val registerResponseCall: Call<RegisterResponse> = apiClient.getRegister().registerUser(registerRequest)

        registerResponseCall.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                Log.d("brearey", "Response received: ${response.body()}")
                if (response.isSuccessful) {
                    Toast.makeText(this@SignupActivity, "Все ок", Toast.LENGTH_LONG).show()
                    finish()
                }
                else {
                    Toast.makeText(this@SignupActivity, "Что-то пошло не так", Toast.LENGTH_LONG).show()
                    Log.d("brearey", "Don't success: ${response.errorBody().toString()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("brearey", "Failed", t)
                Toast.makeText(this@SignupActivity, "Регистрация прошла успешно", Toast.LENGTH_LONG).show()
                val intent:Intent = Intent(this@SignupActivity, SigninActivity::class.java)
                startActivity(intent)
            }
        })
    }

    //Функция вывода диалогового окна с сообщением
    private fun showArlertDialogWindow(message: String) {
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