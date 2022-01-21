package ipca.inventario.cloudmanager

import org.json.JSONObject

class Client {

    var id      : String? = null
    var name    : String? = null
    var local : String? = null
    var date   : String? = null

    fun toJson() : JSONObject{
        val jsonObject = JSONObject()
        jsonObject.put("id"     , id     )
        jsonObject.put("name"   , name   )
        jsonObject.put("location", local)
        jsonObject.put("date"  , date  )

        return jsonObject
    }

    companion object{
        fun fromJson( jsonObject: JSONObject) : Client {
            val client = Client()
            client.id      = jsonObject.getString("id"      )
            client.name    = jsonObject.getString("name"    )
            client.local = jsonObject.getString("location" )
            client.date   = jsonObject.getString("date"   )

            return client
        }
    }

    fun statsClient(): Int{

        var clientList = arrayListOf<Client>()

        Backend.getAllClients {
            clientList = it as ArrayList<Client>
        }
        return clientList.size
    }
}