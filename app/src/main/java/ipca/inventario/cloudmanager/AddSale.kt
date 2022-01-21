package ipca.inventario.cloudmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddSale: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_sale)

        val addButton = findViewById<Button>(R.id.addSale)
        val cancel = findViewById<Button>(R.id.cancel)

        var modelInput = findViewById<EditText>(R.id.sale_model_Input)
        var serialInput = findViewById<EditText>(R.id.sale_p_serial_Input)
        var valueInput = findViewById<EditText>(R.id.s_value_Input)
        var dateInput = findViewById<EditText>(R.id.s_date_Input)
        var cliInput = findViewById<EditText>(R.id.s_cliID_Input)

        addButton.setOnClickListener {
            val sale = Sale()
            sale.serial = Integer.parseInt(serialInput.text.toString())
            sale.model = modelInput.text.toString()
            sale.value = Integer.parseInt(valueInput.text.toString())
            sale.date = dateInput.text.toString()
            sale.cliId = cliInput.text.toString()

            Backend.addNewSale(sale){
                var aux = it
            }
        }

        cancel.setOnClickListener {
            val intent = Intent(this@AddSale, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}