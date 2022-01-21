package ipca.inventario.cloudmanager

import org.json.JSONObject

class Product {

    var serial     : Int? = null
    var model    : String? = null
    var value : Int? = null


    fun toJson() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("serial", serial)
        jsonObject.put("valor", value)
        jsonObject.put("model", model)

        return jsonObject
    }

    companion object{
        fun fromJson( jsonObject: JSONObject) : Product {
            val product = Product()
            product.serial = jsonObject.getInt("serial")
            product.value  = jsonObject.getInt("valor")
            product.model = jsonObject.getString("model")

            return product
        }
    }
}