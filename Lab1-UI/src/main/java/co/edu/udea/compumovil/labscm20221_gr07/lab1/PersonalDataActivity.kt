package co.edu.udea.compumovil.labscm20221_gr07.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        findViewById<EditText>(R.id.select_fecha).setOnClickListener { showDatePickerDialog() }

        findViewById<Button>(R.id.button_sgte).setOnClickListener {
            val intent = Intent(this@PersonalDataActivity, ContactDataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }
    private fun onDateSelected(day: Int, month: Int, year: Int) {
        findViewById<EditText>(R.id.select_fecha).setText("$day-$month-$year")
    }
}

