package ma.quotidien.android.agendaapp.adapter

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ma.quotidien.android.agendaapp.HomeActivity
import ma.quotidien.android.agendaapp.R


class broadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val i=Intent(context,HomeActivity::class.java)
        intent!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pending=PendingIntent.getActivity(context,0,i,0)

        val contentText = intent?.getStringExtra(NOTIFICATION_CONTENT_KEY) ?: "Default Content"
        val id =intent?.getIntExtra("id",0)?:0


        var builder= NotificationCompat.Builder(context!!,"notification")
           .setSmallIcon(R.drawable.ajouter)
           .setContentTitle("Tache Notification")
           .setContentText("Tache : $contentText")
           .setAutoCancel(true)
           .setDefaults(NotificationCompat.DEFAULT_ALL)
           .setPriority(NotificationCompat.PRIORITY_DEFAULT)
           .setContentIntent(pending)

        val notifManager=NotificationManagerCompat.from(context)


        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Veuillez accepter", Toast.LENGTH_SHORT).show()
            return
        }
        notifManager.notify(id,builder.build())
    }
    companion object {
        // Global variable accessible within the class
        var NOTIFICATION_CONTENT_KEY = "notification_content"
    }
}