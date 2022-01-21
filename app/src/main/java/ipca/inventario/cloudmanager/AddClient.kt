package ipca.inventario.cloudmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddClient: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_client)

        val addButton = findViewById<Button>(R.id.addCliButton)
        val cancel = findViewById<Button>(R.id.cancelCliB)

        var cliName = findViewById<EditText>(R.id.cli_name_Input)
        var cliLocal = findViewById<EditText>(R.id.cli_local_Input)
        var cliDate = findViewById<EditText>(R.id.cli_date_Input)

        addButton.setOnClickListener {
            var client = Client()
            client.name = cliName.text.toString()
            client.local = cliLocal.text.toString()
            client.date = cliDate.text.toString()

            Backend.addNewClient(client){
                var aux = it
            }
        }

        cancel.setOnClickListener {
            val intent = Intent(this@AddClient, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}