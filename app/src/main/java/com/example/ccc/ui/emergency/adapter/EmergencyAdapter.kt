package com.example.ccc.ui.emergency.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.model.EmergencyModel


class EmergencyAdapter(val context: Context, private val emergencyContacts: ArrayList<EmergencyModel>): RecyclerView.Adapter<EmergencyAdapter.ViewHolder>() {

    var onClick:((String)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.card_emergency
            ,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listPosition = emergencyContacts[position]
        ("Tel: "+ listPosition.contacts).also {
            holder.contacts.text = it
        }
        Glide.with(context)
            .load(listPosition.display)
            .into(holder.display)
        holder.type.text = listPosition.type
    }

    override fun getItemCount(): Int {
        return emergencyContacts.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val call: ImageView = itemView.findViewById(R.id.emergency_call)

        init {
            call.setOnClickListener{
                onClick?.invoke(emergencyContacts[adapterPosition].contacts)
            }
        }
        val display: ImageView = itemView.findViewById(R.id.lost_item_pic)
        val type: TextView = itemView.findViewById(R.id.emergency_type)
        val contacts: TextView = itemView.findViewById(R.id.emergency_contacts)

    }

}