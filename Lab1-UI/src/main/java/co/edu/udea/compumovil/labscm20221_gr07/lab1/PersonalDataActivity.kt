package co.edu.udea.compumovil.labscm20221_gr07.lab1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import java.util.*


class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        goToContactActivity()

    }

    private fun goToContactActivity() {

        val button:Button = findViewById(R.id.nextB)
        val name:EditText = findViewById(R.id.nombreI)
        val lastName: EditText = findViewById(R.id.apellido)
        val educationLevel: Spinner = findViewById(R.id.educacionL)
        val gender: RadioGroup = findViewById(R.id.g)
        val date: DatePicker = findViewById(R.id.date_input)
        lateinit var radioButton: RadioButton


        button.setOnClickListener{
            if(name.text.isNotEmpty() && lastName.text.isNotEmpty() && date.isNotEmpty()) {
                val intent = Intent(this, ContactDataActivity::class.java)
                val birth_date: String
                val day = date.dayOfMonth
                val month = date.month
                val year = date.year
                val calendar: Calendar = Calendar.getInstance()
                val yearCurrent: Int = calendar.get(Calendar.YEAR)

                if(year >= yearCurrent){
                    showErrorDialog()
                }else {
                    birth_date = day.toString() + "/" + month.toString() + "/" + year.toString()
                    val selectedOption: Int = gender.checkedRadioButtonId
                    if(selectedOption != -1) {
                        radioButton = findViewById(selectedOption)
                        intent.putExtra("gender", radioButton.text)
                    }

                    intent.putExtra("nombre", name.text.toString())
                    intent.putExtra("apellido", lastName.text.toString())
                    intent.putExtra("fecha", birth_date)

                    intent.putExtra("educacion", educationLevel.selectedItem.toString())
                    startActivity(intent)
                }
            }else{
                showDialog()
            }
        }
    }

    private fun showDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialog)
        builder.setMessage(R.string.mensajePersonal)
        builder.setPositiveButton(R.string.ok, DialogInterface.OnClickListener{ dialog, which ->
            Toast.makeText(this,"", Toast.LENGTH_LONG).show()
        })
        builder.show()
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.dialog)
        builder.setMessage(R.string.ErrorFecha)
        builder.setPositiveButton(
            R.string.ok,
            DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "", Toast.LENGTH_LONG).show()
            })
        builder.show()
    }
    }

