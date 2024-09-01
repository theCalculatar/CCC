package com.example.ccc.ui.chat.adapter


import android.content.Context
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ccc.R
import com.example.ccc.ui.chat.model.MessageEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class InMessagingAdapter(private val context: Context,
                         private val messageList: List<MessageEntity>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object{
        private const val ITEM_SENT = 1
        private const val ITEM_RECEIVED = 2
        private const val BEGINNING_OF_MESSAGE_TEXT = 3
    }

    var messageClick:((Int, MessageEntity)->Unit)? = null

    private val chatId = FirebaseAuth.getInstance().currentUser!!.uid
    private val dbUsers = Firebase.firestore.collection("Users")
//    private val dbRefAdmin = Firebase.firestore.collection("Admin")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == 1){
            //item sent
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_sent_message, parent, false)
            SentViewHolder(view)
        }else if (viewType ==2){
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_received_message, parent, false)
            ReceiveViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_beginning_of_chat_text, parent, false)
            BeginningOfText(view                                   )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (holder.javaClass == SentViewHolder::class.java){
            val currentMessage = messageList[position-1]

            val time = currentMessage.time.toString().split(":")

            holder as SentViewHolder
            holder.sentMessage.text = currentMessage.body
            "${time[0]}:${time[1]}".also {
                holder.time.text = it
            }
        }
        else if (holder.javaClass == ReceiveViewHolder::class.java){
            val currentMessage = messageList[position-1]

            val time = currentMessage.time.toString().split(":")
            holder as ReceiveViewHolder
            holder.receivedMessage.text = currentMessage.body
            "${time[0]}:${time[1]}".also {
                holder.time.text = it
            }

            dbUsers.document(currentMessage.senderId).get()
                .addOnSuccessListener { receiver->
                    if (receiver.exists()){
                        val image = receiver["picture"]
                        Glide.with(context)
                            .load(image)
                            .placeholder(R.drawable.ic_baseline_person_24)
                            .into(holder.picture)
                    }
                }


        }
    }

    override fun getItemCount(): Int {
        return messageList.size+1
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = if(messageList.isNotEmpty()&&position>0){
            messageList[(position-1)]
        } else {
            null
        }

        return if (position==0){
        BEGINNING_OF_MESSAGE_TEXT
        } else if(position>0 && chatId == currentMessage?.senderId){
            ITEM_SENT
        }else{
            ITEM_RECEIVED
        }
    }

    inner class SentViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){


        val sentMessage: TextView = itemView.findViewById(R.id.sent_text_message)
        val time: TextView = itemView.findViewById(R.id.time_message_sent)
        init {
            itemView.setOnLongClickListener {
                itemView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                false
            }
            itemView.setOnCreateContextMenuListener { menu, v, menuInfo ->
                MenuInflater(context).inflate(R.menu.message_menu,menu)
                menu.forEach {
                    it.setOnMenuItemClickListener { menuItem->
                        messageClick?.invoke(menuItem.itemId,messageList[adapterPosition-1])
                        true
                    }
                }
            }
        }
    }
    inner class ReceiveViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

        val receivedMessage: TextView = itemView.findViewById(R.id.received_text_message)
        val time: TextView = itemView.findViewById(R.id.time_message_sent)
        val picture: ImageView = itemView.findViewById(R.id.person_chatting_picture)


    }
    inner class BeginningOfText(itemView:View): RecyclerView.ViewHolder(itemView){
    }


}