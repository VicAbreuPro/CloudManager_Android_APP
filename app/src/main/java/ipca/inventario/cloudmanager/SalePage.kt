package ipca.inventario.cloudmanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SalePage: AppCompatActivity() {

    // Create arraylist of Product Object for Product List
    var salesList = arrayListOf<Sale>()
    var adapter : SaleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sale_list)

        adapter = SaleAdapter()
        val listViewSales = findViewById<ListView>(R.id.saleList)

        listViewSales.adapter = adapter

        val logoutButton = findViewById<ImageView>(R.id.logoutS)

        logoutButton.setOnClickListener {
            val intent = Intent(this@SalePage, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume(){
        super.onResume()

        Backend.getAllSales {
            salesList = it as ArrayList<Sale>
            adapter?.notifyDataSetChanged()
        }

    }
    // Class adapter for adapt our contact list to a listview
    inner class SaleAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return salesList.size
        }

        override fun getItem(position: Int): Any {
            return salesList[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, parent: View?, viewGroup: ViewGroup?): View {
            // Create variable of type Object View with layout inflater(using the previous created xml "object item") to return from this function
            val salesView = layoutInflater.inflate(R.layout.s_list_item, viewGroup, false)

            //Create a variable for xml("s_list_item") text view "sale id"
            val saleId = salesView.findViewById<TextView>(R.id.sale_id)

            //Create a variable for text view sale product
            val saleProduct = salesView.findViewById<TextView>(R.id.sale_p)

            //Create variable for text view sale date
            val saleDate = salesView.findViewById<TextView>(R.id.s_date)

            //Create variable for text view sale client id
            val saleCliId = salesView.findViewById<TextView>(R.id.s_cli)

            //Create variable for text view sale value
            val saleValue = salesView.findViewById<TextView>(R.id.s_value)


            // Associate the name and quantity text view to information of contact in product list
            saleId.text = salesList[position].id
            saleProduct.text = salesList[position].model
            saleDate.text = salesList[position].date
            saleCliId.text = salesList[position].cliId
            saleValue.text = salesList[position].value.toString()

            //clientView.isClickable = true

            /*clientView.setOnClickListener {
                val intent = Intent(this@MainActivity, ContactPage::class.java)
                intent.putExtra("c_name", contactList[position].name)
                intent.putExtra("c_surname", contactList[position].surname)
                intent.putExtra("c_phone", contactList[position].phone)
                intent.putExtra("c_img", contactList[position].image)
                startActivity(intent)
            }*/
            return salesView
        }
    }
}