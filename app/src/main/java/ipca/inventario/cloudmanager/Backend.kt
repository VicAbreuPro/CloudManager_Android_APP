package ipca.inventario.cloudmanager

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject


object Backend {

    const val BASE_API = "https://apivna20.azurewebsites.net/"


    fun getAllClients(callback: (List<Client>)->Unit) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API+ "Client/ClientList") //define URL to access specific URL in API service

                .build()
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful){
                    val result = response.body!!.string()
                    val clientJsonArray = JSONArray(result)
                    val clients = arrayListOf<Client>()
                    for( index in 0 until clientJsonArray.length()){
                        val client = Client.fromJson(clientJsonArray[index] as JSONObject)
                        clients.add(client)
                    }

                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(clients)
                    }
                }
            }
        }
    }

    fun getAllProducts(callback: (List<Product>)->Unit) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API+ "Product/ProductList") //define URL to access specific URL in API service
                .build()
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful){
                    val result = response.body!!.string()
                    val clientJsonArray = JSONArray(result)
                    val products = arrayListOf<Product>()
                    for( index in 0 until clientJsonArray.length()){
                        val product = Product.fromJson(clientJsonArray[index] as JSONObject)
                        products.add(product)
                    }

                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(products)
                    }
                }
            }
        }
    }

    fun getAllSales(callback: (List<Sale>)->Unit) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API+ "Sales/SalesList") //define URL to access specific URL in API service
                .build()
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful){
                    val result = response.body!!.string()
                    val clientJsonArray = JSONArray(result)
                    val sales = arrayListOf<Sale>()
                    for( index in 0 until clientJsonArray.length()){
                        val sale = Sale.fromJson(clientJsonArray[index] as JSONObject)
                        sales.add(sale)
                    }

                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(sales)
                    }
                }
            }
        }
    }

    // Verify User
    fun verifyUser(username : String, password : String, callback: (Boolean) -> Unit){

    }

    // Add New Client
    fun addNewClient(client: Client, callback: (Boolean)->Unit ) {
        GlobalScope.launch (Dispatchers.IO) {

            val postBody = client.toJson().toString()
            val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Client/AddClient")
                .post(postBody.toRequestBody("application/json; charset=utf-8".toMediaType()))
                .build()
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful){
                    val result = response.body!!.string()
                    Log.d("clientAdd", result)
                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(true)
                    }
                }else {
                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(false)
                    }
                }
            }
        }
    }

    // Add New Sale
    fun addNewSale(sale : Sale, callback: (Boolean)->Unit ) {
        GlobalScope.launch (Dispatchers.IO) {

            val postBody = sale.toJson().toString()
            val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Client/AddSale")
                .post(postBody.toRequestBody("application/json; charset=utf-8".toMediaType()))
                .build()
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful){
                    val result = response.body!!.string()
                    Log.d("saleAdd", result)
                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(true)
                    }
                }else {
                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(false)
                    }
                }
            }
        }
    }

    // Add New Product
    fun addNewProduct(product : Product, callback: (Boolean)->Unit ) {
        GlobalScope.launch (Dispatchers.IO) {

            val postBody = product.toJson().toString()
            val okHttpClient = OkHttpClient()
            val request = Request.Builder()
                .url(BASE_API + "Client/AddProduct")
                .post(postBody.toRequestBody("application/json; charset=utf-8".toMediaType()))
                .build()
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful){
                    val result = response.body!!.string()
                    Log.d("productAdd", result)
                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(true)
                    }
                }else {
                    GlobalScope.launch (Dispatchers.Main){
                        callback.invoke(false)
                    }
                }
            }
        }
    }

}