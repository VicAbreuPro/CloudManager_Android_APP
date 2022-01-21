package ipca.inventario.cloudmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddProduct: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)

        val addButton = findViewById<Button>(R.id.addProduct)
        val cancel = findViewById<Button>(R.id.cancelB)

        var pModel = findViewById<EditText>(R.id.product_model_Input)
        var pSerial = findViewById<EditText>(R.id.p_serial_Input)
        var pValue = findViewById<EditText>(R.id.p_value_Input)

        addButton.setOnClickListener {
            var product = Product()
            product.model = pModel.text.toString()
            product.serial = Integer.parseInt(pSerial.text.toString())
            product.value = Integer.parseInt(pValue.text.toString())

            Backend.addNewProduct(product){
                var aux = it
            }
        }

        cancel.setOnClickListener {
            val intent = Intent(this@AddProduct, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}