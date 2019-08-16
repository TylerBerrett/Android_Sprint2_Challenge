package com.lambdaschool.sprint2_challenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
            println(adapter.getListItems())
        }




    }
}
