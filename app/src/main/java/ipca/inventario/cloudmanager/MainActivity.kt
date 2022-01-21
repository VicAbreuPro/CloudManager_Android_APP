package ipca.inventario.cloudmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create button variable for login button
        val loginButton = findViewById<Button>(R.id.login_button)

        var userName = findViewById<EditText>(R.id.usernameInput)
        var passWord = findViewById<EditText>(R.id.passwordInput)

        //Activate Buttons
        loginButton.setOnClickListener {

            Backend.verifyUser(userName.text.toString(), passWord.text.toString()){
                var aux = it
            }
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}