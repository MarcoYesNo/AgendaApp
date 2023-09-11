package ma.quotidien.android.agendaapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.storeuaz.data.Programme
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private var listeProg= emptyList<Programme>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottom = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navController = findNavController(this, R.id.fragmentContainerView)
        setupWithNavController(bottom, navController)


    }

//    private fun setCancel(){
//        alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
//        val intent=Intent(this,broadcast::class.java)
//
//        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)
//
//        alarmManager.cancel(pendingIntent)
//        Toast.makeText(this, "annuler tout", Toast.LENGTH_SHORT).show()
//    }

//    private fun setAlarm(calendar: Calendar) {
//        alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
//        val intent=Intent(this,broadcast::class.java)
//
//        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)
//
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY,pendingIntent
//        )
//        Toast.makeText(this, "Activer avec succes l'alarme ", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun createNotif() {
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
//            val name:CharSequence="agendaReminderChannel"
//            val description="Channel for Agenda App"
//            val importance=NotificationManager.IMPORTANCE_DEFAULT
//            val channel=NotificationChannel("notification",name,importance)
//            channel.description=description
//
//            val  notifManager=getSystemService(
//                NotificationManager::class.java
//            )
//
//            notifManager.createNotificationChannel(channel)
//        }
//    }
override fun onBackPressed() {
    val inte= Intent(this,LoginActivity::class.java)
    startActivity(inte)
    finish()
}
}

