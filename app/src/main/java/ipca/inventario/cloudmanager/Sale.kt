package ipca.inventario.cloudmanager

import org.json.JSONObject

class Sale {
    var id      : String? = null
    var serial  : Int? = null
    var model   : String? = null
    var date    : String? = null
    var cliId   : String? = null
    var value   : Int? = null

    fun toJson() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("sale_id"  , id     )
        jsonObject.put("serial"   , serial )
        jsonObject.put("valor"    , value  )
        jsonObject.put("model"    , model  )
        jsonObject.put("client_id", cliId  )
        jsonObject.put("date"     , date  )

        return jsonObject
    }

    companion object{
        fun fromJson( jsonObject: JSONObject) : Sale {
            val sale = Sale()
            sale.id      = jsonObject.getString("sale_id")
            sale.serial  = jsonObject.getInt("serial")
            sale.model   = jsonObject.getString("model")
            sale.date    = jsonObject.getString("date")
            sale.cliId   = jsonObject.getString("client_id")
            sale.value   = jsonObject.getInt("valor")

            return sale
        }
    }
}