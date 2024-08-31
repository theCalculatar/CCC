package com.example.ccc.ui.crime.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.model.Crime
import com.example.ccc.model.ReportModel
import com.google.firebase.firestore.FirebaseFirestore


class CrimeAdapter(val context: Context, private val crimes: ArrayList<Crime>): RecyclerView.Adapter<CrimeAdapter.ViewHolder>() {

    val dbRef = FirebaseFirestore.getInstance()


    var onClick:((Crime)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.card_crime
            ,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listPosition = crimes[position]
        holder.title.text = listPosition.type
        (""+ listPosition.description).also {
            holder.dropOff.text = it
        }
        dbRef.collection("Users").document(listPosition.userId).get().addOnSuccessListener { document->
            Glide.with(context)
                .load(document.data?.get("picture"))
                .into(holder.display)
        }
        holder.publisherName.text = listPosition.userName
    }

    override fun getItemCount(): Int {
        return crimes.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                onClick?.invoke(crimes[adapterPosition])
            }
        }
        val title: TextView = itemView.findViewById(R.id.lost_item_type)
        val publisherName: TextView = itemView.findViewById(R.id.uploader_name)
        val display: ImageView = itemView.findViewById(R.id.lost_item_pic)
        val dropOff: TextView = itemView.findViewById(R.id.drop_off)
    }

}