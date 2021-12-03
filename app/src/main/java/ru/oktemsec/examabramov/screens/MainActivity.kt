package ru.oktemsec.examabramov.screens

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.oktemsec.examabramov.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSee:Button = findViewById(R.id.btn_see)
        btnSee.setOnClickListener {
            ShowArlertDialogWindow("Разработчик: Абрамов Евгений Семенович \nМБНОУ Октемский НОЦ")
        }
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