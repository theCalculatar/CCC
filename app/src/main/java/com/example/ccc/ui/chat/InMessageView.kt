package com.example.ccc.ui.chat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccc.R
import com.example.ccc.databinding.ActivityInMessageViewBinding
import com.example.ccc.ui.chat.adapter.InMessagingAdapter
import com.example.ccc.ui.chat.model.MessageEntity
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class InMessageView : AppCompatActivity() {

    private lateinit var  binding: ActivityInMessageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInMessageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatViewModel =
            ViewModelProvider(this)[InMessageViewModel::class.java]

        val chatRoomId = "Community"

        val recyclerView = binding.inMessagesView
        val messageToSend = binding.inputTextView.messageBox
        val sendButton = binding.inputTextView.sendButton
        val chatId = FirebaseAuth.getInstance().currentUser!!.uid

        binding.backButton.setOnClickListener{
            finish()
        }
//        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]



        var messageList = ArrayList<MessageEntity>(0)

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        chatViewModel.getMessages(chatRoomId).observe(this){ list->
                messageList = list as ArrayList<MessageEntity>

                val adapter = InMessagingAdapter(this,messageList)
                recyclerView.adapter = adapter
                adapter.messageClick = {action,message->
                    Log.d("lee",message.toString())
                    when(action){
                        R.id.delete_msg -> message.messagingId?.let {
                            chatViewModel.deleteMessage(it)
                        }
                        else -> {
                            EditMessageModal(message).show(supportFragmentManager,"Edit_message_frag")
                        }
                    }
                }

                adapter.notifyItemInserted(list.size)
                recyclerView.scrollToPosition(messageList.size-1)
            }

        sendButton.setOnClickListener{
            //get time
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val formatted = current.format(formatter)

            if(messageToSend.text.toString() != ""){

//                messageList.add(
//                    MessageEntity(null,chatId, chatRoomId,
//                    messageToSend.text.toString(),formatted)
//                )
                recyclerView.scrollToPosition(messageList.size-1)

                chatViewModel.insertMessages(
                    MessageEntity(null,chatId,chatRoomId,messageToSend.text.toString(),formatted)
                )
                messageToSend.text.clear()
            }
        }

    }
}