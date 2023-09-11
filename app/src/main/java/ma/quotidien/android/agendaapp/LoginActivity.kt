package ma.quotidien.android.agendaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.storeuaz.data.User
import com.example.storeuaz.data.UserViewModel
import ma.quotidien.android.agendaapp.adapter.listeAdapter

lateinit var sharedPreferences: SharedPreferences
var idLogin=0

class LoginActivity : AppCompatActivity() {

    private var listeUser= emptyList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences=getSharedPreferences("prefs",Context.MODE_PRIVATE)
        idLogin= sharedPreferences.getInt("idLog",0)

        val btnConnect=findViewById<Button>(R.id.btnConnect)
        val btnCreate=findViewById<TextView>(R.id.txtSign)
        val txtNom=findViewById<EditText>(R.id.txtUser)
        val mdp=findViewById<EditText>(R.id.txtMdp)

        var mUserViewModel= ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.readAllData.observe(this) { user ->
            listeUser = user
        }

        btnConnect.setOnClickListener {
            for (item in listeUser) {
                if (txtNom.text.toString() == item.pseudo.toString() && mdp.text.toString() == item.mdp.toString() && !txtNom.text.toString().isNullOrEmpty()) {

                    val editor:SharedPreferences.Editor= sharedPreferences.edit()

                    idLogin=item.id
                    editor.putInt("idLog",item.id)

                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                    }
                else{
                    Toast.makeText(this, "Verifier votre pseudo ou mot de passse", Toast.LENGTH_SHORT).show()

                }
            }
        }
        btnCreate.setOnClickListener {
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }

    }
}