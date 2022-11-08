package com.example

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.coderem.R

const val notificationID = 1
    const val channelID = "channel1"
    const val titleExtra = "titleExtra"
    const val messageExtra = "messageExtra"

    class Notification : BroadcastReceiver()
    {
        override fun onReceive(context: Context, intent: Intent)
        {
            val notification = NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(intent.getStringExtra(titleExtra))
                .setContentText(intent.getStringExtra(messageExtra))
//                .setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.cute_notification))
                .setDefaults(0)
                .build()



            val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify((1).toInt(), notification)
        }

    }
