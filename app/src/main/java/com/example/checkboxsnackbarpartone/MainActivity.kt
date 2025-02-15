package com.example.checkboxsnackbarpartone

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var inputDataET: EditText
    private lateinit var saveDataBTN: Button
    private lateinit var savedDataTV: TextView
    private lateinit var clearDataBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputDataET = findViewById(R.id.inputDataET)
        saveDataBTN = findViewById(R.id.saveDataBTN)
        savedDataTV = findViewById(R.id.savedDataTV)
        clearDataBTN = findViewById(R.id.clearDataBTN)
        saveDataBTN.setOnClickListener(this)
        clearDataBTN.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.saveDataBTN -> {
                val imm =
                    p0.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(p0.windowToken, 0)
                savedDataTV.text = inputDataET.text.toString()
                inputDataET.text = null
            }

            R.id.clearDataBTN -> {
                val imm =
                    p0.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(p0.windowToken, 0)

                if (savedDataTV.text == null || savedDataTV.text == "") {
                    Snackbar.make(p0, "Данные отсуствуют", Snackbar.LENGTH_SHORT).show()
                } else {
                    val alert = Snackbar
                        .make(p0, "Подтвердите удаление:", Snackbar.LENGTH_SHORT)
                        .setAction("Удалить") {
                            savedDataTV.text = null
                            Snackbar.make(p0, "Данные удалены", Snackbar.LENGTH_SHORT).show()
                        }
                    alert.setActionTextColor(Color.RED).show()
                }
            }
        }
    }
}