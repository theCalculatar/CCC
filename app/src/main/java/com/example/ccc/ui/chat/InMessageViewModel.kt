package com.example.ccc.ui.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.medinow.ui.chat.model.Admin
import com.example.ccc.ui.chat.model.MessageEntity
import com.example.ccc.ui.chat.repo.MessagingRepo

class InMessageViewModel( application: Application) : AndroidViewModel(application) {
    private var appDatabase = MessagingRepo()

    fun getMessages(chatRoomId:String):LiveData<List<MessageEntity>>{
        return appDatabase.getMessages(chatRoomId)
    }

    fun insertMessages(message: MessageEntity) {
        appDatabase.insertMessage(message)
    }

//    fun newChat(newPerson: PreviousChatsEntity){
//        appDatabase.insertNew(newPerson)
//    }

//    fun updateAdmin(message: String, nameUser: String){
//        appDatabase.sendNotification(nameUser," unknown ",message)
//    }
    fun getAdminDetails(chatRoomId: String): LiveData<Admin> {
        return appDatabase.getAdminDetails(chatRoomId)
    }

}