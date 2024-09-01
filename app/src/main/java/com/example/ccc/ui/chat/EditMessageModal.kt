package com.example.ccc.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.ccc.R
import com.example.ccc.ui.chat.model.MessageEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EditMessageModal(val message: MessageEntity) :BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_msg,container)
        val sendButton = view.findViewById<FloatingActionButton>(R.id.send_button)
        val editMessage = view.findViewById<EditText>(R.id.message_box)
        val chatViewModel =
            ViewModelProvider(this)[InMessageViewModel::class.java]
        //pre fill with text
        editMessage.setText(message.body, TextView.BufferType.EDITABLE)

        sendButton.setOnClickListener {
            if (editMessage.text.toString().trim() != "") {
                chatViewModel.updateMessage(
                    MessageEntity(
                        message.messagingId,
                        message.senderId,
                        message.roomId,
                        editMessage.text.toString(),
                        message.time
                    )
                )
                editMessage.text.clear()
                dismiss()
            }
        }
        return view
    }
}