package ipca.inventario.cloudmanager

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductPage: AppCompatActivity() {

    // Create arraylist of Product Object for Product List
    var productList = arrayListOf<Product>()
    var adapter : ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_list)

        adapter = ProductAdapter()
        val listViewProducts = findViewById<ListView>(R.id.productList)

        listViewProducts.adapter = adapter

        val logoutButton = findViewById<ImageView>(R.id.logoutP)

        logoutButton.setOnClickListener {
            val intent = Intent(this@ProductPage, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume(){
        super.onResume()

        Backend.getAllProducts {
            productList = it as ArrayList<Product>
            adapter?.notifyDataSetChanged()
        }

    }
    // Class adapter for adapt our contact list to a listview
    inner class ProductAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return productList.size
        }

        override fun getItem(position: Int): Any {
            return productList[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, parent: View?, viewGroup: ViewGroup?): View {
            // Create variable of type Object View with layout inflater(using the previous created xml "object item") to return from this function
            val productView = layoutInflater.inflate(R.layout.p_list_item, viewGroup, false)

            //Create a variable for xml("p_list_item") text view "serial number"
            val productSerial = productView.findViewById<TextView>(R.id.serial_number)

            //Create a variable for text view product model
            val productModel = productView.findViewById<TextView>(R.id.p_model)

            //Create variable for text view product value
            val productValue = productView.findViewById<TextView>(R.id.p_value)


            // Associate the name and quantity text view to information of contact in product list
            productSerial.text = productList[position].serial.toString()
            productModel.text = productList[position].model
            productValue.text = productList[position].value.toString()

            //clientView.isClickable = true

            /*clientView.setOnClickListener {
                val intent = Intent(this@MainActivity, ContactPage::class.java)
                intent.putExtra("c_name", contactList[position].name)
                intent.putExtra("c_surname", contactList[position].surname)
                intent.putExtra("c_phone", contactList[position].phone)
                intent.putExtra("c_img", contactList[position].image)
                startActivity(intent)
            }*/
            return productView
        }
    }
}