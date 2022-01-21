package ipca.inventario.cloudmanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ClientPage: AppCompatActivity() {

    //Create arraylist of Client Object fo client list
    var clientList = arrayListOf<Client>()
    var adapter : ClientAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_list)

        adapter = ClientAdapter()
        val listViewClients = findViewById<ListView>(R.id.cliList)

        listViewClients.adapter = adapter

        val logoutButton = findViewById<ImageView>(R.id.logoutCli)

        logoutButton.setOnClickListener {
            val intent = Intent(this@ClientPage, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // Get List of clients from backend
        Backend.getAllClients {
            clientList = it as ArrayList<Client>
            adapter?.notifyDataSetChanged()
        }

    }

    // Class adapter for adapt our contact list to a listview
    inner class ClientAdapter : BaseAdapter() {
        override fun getCount() : Int {
            return clientList.size
        }

        override fun getItem(position : Int): Any{
            return clientList[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, parent: View?, viewGroup: ViewGroup?): View {
            // Create variable of type Object View with layout inflater(using the previous created xml "object item") to return from this function
            val clientView = layoutInflater.inflate(R.layout.list_item, viewGroup, false)

            //Create a variable for xml("contact item") text view "client name"
            val clientName = clientView.findViewById<TextView>(R.id.cli_name)

            //Create a variable for text view client location
            val clientLocation = clientView.findViewById<TextView>(R.id.cli_location)

            //Create variable for text view client register date
            val clientDate = clientView.findViewById<TextView>(R.id.cli_regis_date)

            //Create variable for text view client ID
            val clientID = clientView.findViewById<TextView>(R.id.cli_id)

            // Associate the name and quantity text view to information of contact in product list
            clientName.text = clientList[position].name
            clientLocation.text = clientList[position].local
            clientDate.text = clientList[position].date
            clientID.text =clientList[position].id

            //clientView.isClickable = true

            /*clientView.setOnClickListener {
                val intent = Intent(this@MainActivity, ContactPage::class.java)
                intent.putExtra("c_name", contactList[position].name)
                intent.putExtra("c_surname", contactList[position].surname)
                intent.putExtra("c_phone", contactList[position].phone)
                intent.putExtra("c_img", contactList[position].image)
                startActivity(intent)
            }*/
            return clientView
        }
    }
}