package ma.quotidien.android.agendaapp.fragments

import android.app.*
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.storeuaz.data.Programme
import com.example.storeuaz.data.UserViewModel
import ma.quotidien.android.agendaapp.R
import ma.quotidien.android.agendaapp.adapter.broadcast
import ma.quotidien.android.agendaapp.idLogin
import java.util.*


class Ajouter_Fragment : Fragment() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_ajouter_, container, false)

        var mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        var date1=Calendar.getInstance()
        var dateAlarm=Calendar.getInstance()
        var mYear: Int=0
        var mMonth: Int=0
        var mDay: Int=0
        var mHour:Int=0
        var mMinute:Int=0
        var IdCHANNEL:Int=0

        val btnDate=view.findViewById<Button>(R.id.btnDate)
        val btnH=view.findViewById<Button>(R.id.btnTime)
        val btnEnr=view.findViewById<Button>(R.id.btnAjoutTache)
        val txtDate=view.findViewById<TextView>(R.id.txtDate)
        val txtHeure=view.findViewById<TextView>(R.id.txtTime)
        val contenu=view.findViewById<EditText>(R.id.txtTache)

        btnDate.setOnClickListener {
            var c: Calendar = Calendar.getInstance()
             mYear = c.get(Calendar.YEAR)
             mMonth = c.get(Calendar.MONTH)
             mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(view.context,
                { view, year, monthOfYear, dayOfMonth ->
                    txtDate.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year

                    date1.set(Calendar.YEAR, year+1900);
                    date1.set(Calendar.MONTH,monthOfYear+1)
                    date1.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                    dateAlarm.set(Calendar.YEAR,year)
                    dateAlarm.set(Calendar.MONTH,monthOfYear)
                    dateAlarm.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()

        }
        btnH.setOnClickListener {
            val c = Calendar.getInstance()
             mHour = c[Calendar.HOUR_OF_DAY]
             mMinute = c[Calendar.MINUTE]

            val timePickerDialog = TimePickerDialog(context,
                { view, hourOfDay, minute -> txtHeure.setText("$hourOfDay:$minute")
                    date1.set(Calendar.HOUR_OF_DAY,hourOfDay)
                    date1.set(Calendar.MINUTE,minute)

                    dateAlarm.set(Calendar.HOUR_OF_DAY,hourOfDay)
                    dateAlarm.set(Calendar.MINUTE,minute)

                }, mHour, mMinute, true
            )
            timePickerDialog.show()

        }
        btnEnr.setOnClickListener {
            if(contenu.text.toString()=="" || contenu.text.isNullOrEmpty()){
                Toast.makeText(context, "Veuillez bien remplir", Toast.LENGTH_SHORT).show()
            }
            else{
                val prog=Programme(0, idLogin,contenu.text.toString(),date1.time)
                try {

                    IdCHANNEL= (date1.timeInMillis.toInt())/1000


                    createNotif(view.context)
                    setAlarm(dateAlarm,view.context,contenu.text.toString(),IdCHANNEL)
                    mUserViewModel.addProg(prog)
                    //Toast.makeText(context, "Ajouter avec succes", Toast.LENGTH_SHORT).show()
                    view.findNavController().navigate(R.id.action_ajouter_Fragment_to_home_Fragment)


                }
                catch (e:Exception){
                    Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }
        }
        return view
    }

        private fun setCancel(context: Context,cont:String){
        alarmManager=context.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent=Intent(context,broadcast::class.java)

        pendingIntent=PendingIntent.getBroadcast(context,cont.hashCode(),intent,0)

        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "annuler tout", Toast.LENGTH_SHORT).show()
    }

    private fun setAlarm(calendar: Calendar,context: Context,contenus:String,id:Int) {
        alarmManager=context.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent= Intent(context, broadcast::class.java).apply {
            putExtra(broadcast.NOTIFICATION_CONTENT_KEY, contenus)
            putExtra("id",id)
        }

        //encoyer desc
       // intent.putExtra(broadcast.NOTIFICATION_CONTENT_KEY, contenus)


        pendingIntent= PendingIntent.getBroadcast(context,contenus.hashCode(),intent,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            pendingIntent
        )
        //context.sendBroadcast(intent)
        Toast.makeText(context, "Activer avec succes l'alarme ${calendar.time.minutes}", Toast.LENGTH_SHORT).show()
    }

    private fun createNotif(context: Context) {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val name:CharSequence="agendaReminderChannel"
            val description="Channel for Agenda App"
            val importance= NotificationManager.IMPORTANCE_DEFAULT
            val channel= NotificationChannel("notification",name,importance)
            channel.description=description

            val  notifManager=context.getSystemService(
                NotificationManager::class.java
            )

            notifManager.createNotificationChannel(channel)
        }
    }
}