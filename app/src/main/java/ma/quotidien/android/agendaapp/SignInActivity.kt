package ma.quotidien.android.agendaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.storeuaz.data.User
import com.example.storeuaz.data.UserViewModel

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val txtnom=findViewById<EditText>(R.id.txtUserNew)
        val txtPass=findViewById<EditText>(R.id.txtMdpNew)
        val btn=findViewById<Button>(R.id.btnSign)

        btn.setOnClickListener {
            try {
                var mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
                val user=User(0,txtnom.text.toString(),txtnom.text.toString(),txtPass.text.toString())
                mUserViewModel.addUser(user)
                Toast.makeText(this, "ajouter user avec succes", Toast.LENGTH_SHORT).show()

                val inte=Intent(this,LoginActivity::class.java)
                startActivity(inte)
                finish()
            }
            catch (e:Exception){
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()}
        }
    }

    override fun onBackPressed() {
        val inte=Intent(this,LoginActivity::class.java)
        startActivity(inte)
        finish()
    }
}