package com.lambdaschool.sprint2_challenge

import android.accessibilityservice.GestureDescription
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import com.lambdaschool.sprint2_challenge.recycler.CreateItems
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemsTogether = ArrayList<GroupItems>()

        for (i in ShoppingItemConstants.ITEM_NAMES_RAW.indices){
            itemsTogether.add(GroupItems(ShoppingItemConstants.ICON_IDS[i], ShoppingItemConstants.ITEM_NAMES_RAW[i]))
        }



        val adapter = CreateItems(itemsTogether)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter




            button_send_list.setOnClickListener{
                val theListOfItems = adapter.getListItems()
                createNotification()

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is what I want mom ${theListOfItems.toString().replace("[","").replace("]","")}")
                    type = "text/plain"
                }
                startActivity(sendIntent)
            }




    }








    fun createNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = "channel"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "List"
            val descriptionText = "This is to annoy you"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channel, name, importance)
            channel.description = descriptionText

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.drawable.ic_shopping_bag)
                .setContentTitle("Conformation")
                .setContentText("Your mom is about to hate you")
                .setColor(ContextCompat.getColor(this, R.color.colorAccentLight))
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)

        notificationManager.notify(12, builder.build())
    }





}
