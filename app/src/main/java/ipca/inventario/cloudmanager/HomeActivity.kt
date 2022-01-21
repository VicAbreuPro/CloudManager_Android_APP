package ipca.inventario.cloudmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page_activity)

        //Create Buttons
        val clientButton = findViewById<ImageView>(R.id.client_button)
        val productButton = findViewById<ImageView>(R.id.products_button)
        val salesButton = findViewById<ImageView>(R.id.sales_button)
        val addClient = findViewById<Button>(R.id.add_cli_button)
        val addProduct = findViewById<Button>(R.id.add_p_button)
        val addSale = findViewById<Button>(R.id.add_s_button)
        val logoutButton = findViewById<ImageView>(R.id.logout_button)

        logoutButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, MainActivity::class.java)
            startActivity(intent)
        }

        //Activate Buttons
        addClient.setOnClickListener {
            val intent = Intent(this@HomeActivity, AddClient::class.java)
            startActivity(intent)
        }

        addProduct.setOnClickListener {
            val intent = Intent(this@HomeActivity, AddProduct::class.java)
            startActivity(intent)
        }

        addSale.setOnClickListener {
            val intent = Intent(this@HomeActivity, AddSale::class.java)
            startActivity(intent)
        }

        clientButton.setOnClickListener{
            val intent = Intent(this@HomeActivity, ClientPage::class.java)
            startActivity(intent)
        }

        productButton.setOnClickListener{
            val intent = Intent(this@HomeActivity, ProductPage::class.java)
            startActivity(intent)
        }

        salesButton.setOnClickListener{
            val intent = Intent(this@HomeActivity, SalePage::class.java)
            startActivity(intent)
        }
    }
}