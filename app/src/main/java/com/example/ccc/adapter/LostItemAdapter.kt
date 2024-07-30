package com.example.ccc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.model.ReportModel


class LostItemAdapter(val context: Context, val blogList: ArrayList<ReportModel>): RecyclerView.Adapter<LostItemAdapter.ViewHolder>() {


    var onClick:((ReportModel)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate( R.layout.card_lost_item
            ,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listPosition = blogList[position]
        holder.title.text = listPosition.type
        ("Drop off: "+ listPosition.dropOff).also {
            holder.dropOff.text = it
        }
        Glide.with(context)
            .load(listPosition.display)
            .into(holder.display)
        holder.publisherName.text = listPosition.userName
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                onClick?.invoke(blogList[adapterPosition])
            }
        }
        val title: TextView = itemView.findViewById(R.id.lost_item_type)
        val publisherName: TextView = itemView.findViewById(R.id.uploader_name)
        val display: ImageView = itemView.findViewById(R.id.lost_item_pic)
        val dropOff: TextView = itemView.findViewById(R.id.drop_off)
    }

}