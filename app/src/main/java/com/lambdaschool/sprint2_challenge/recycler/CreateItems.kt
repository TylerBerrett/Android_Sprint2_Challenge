package com.lambdaschool.sprint2_challenge.recycler

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lambdaschool.sprint2_challenge.GroupItems
import com.lambdaschool.sprint2_challenge.ItemsToSend
import com.lambdaschool.sprint2_challenge.R
import kotlinx.android.synthetic.main.list_item.view.*


class CreateItems(val list: ArrayList<GroupItems>) : RecyclerView.Adapter<CreateItems.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p0: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return(ViewHolder(view))
    }

    override fun getItemCount(): Int = list.size

    val theListOfItems = ArrayList<String>()

    override fun onBindViewHolder(holder: ViewHolder, p0: Int) {
        holder.foodImg.setImageResource(list[p0].foodItemImage)
        holder.foodText.text = list[p0].foodItemText


        holder.toogleButton.setOnClickListener {

            if (!holder.toogleButton.isChecked){
                holder.cardColor.setCardBackgroundColor(ContextCompat.getColor(it.context, R.color.colorAccent))
                theListOfItems.add(list[p0].foodItemText)

            }
            else {
                holder.cardColor.setCardBackgroundColor(ContextCompat.getColor(it.context, R.color.colorPrimaryLight))
                theListOfItems.remove(list[p0].foodItemText)
            }
        }



    }

    fun getListItems(): ArrayList<String> {
        return theListOfItems
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val foodImg = itemView.food_image
        val foodText = itemView.food_name
        val toogleButton = itemView.toggle_button
        val cardColor = itemView.card_view
    }

}