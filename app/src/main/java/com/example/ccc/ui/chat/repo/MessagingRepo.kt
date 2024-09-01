package com.example.ccc.ui.chat.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ccc.model.ReportModel
import com.example.medinow.ui.chat.model.Admin
import com.example.ccc.ui.chat.model.MessageEntity
import com.example.ccc.ui.chat.model.PreviousChatsEntity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class MessagingRepo {
    private val dbRef = Firebase.firestore.collection("Messages")
    private val dbRefAdmin = Firebase.firestore.collection("Admin")
    private val dbAlert = Firebase.firestore.collection("Alerts")

    fun getMessages(chatRoomId: String): LiveData<List<MessageEntity>> {
        val messages = MutableLiveData<List<MessageEntity>>()
        dbRef.document(chatRoomId).collection("dialogs")
            .orderBy("time")
            .addSnapshotListener { value, _ ->
                val arrayList = ArrayList<MessageEntity>()
                if (value != null) {
                    for (document in value) {
                        val obj = document.toObject<MessageEntity>()
                        obj.messagingId = document.id
                        arrayList.add(obj)
                    }
                }
                messages.postValue(arrayList)
            }
        return messages
    }
    fun insertMessage(message: MessageEntity) {
        dbRef.document(message.roomId).collection("dialogs")
            .add(message)
    }

    fun getAdminDetails(chatRoomId: String): LiveData<Admin> {
        val admin = MutableLiveData<Admin>()
        dbAlert.document(chatRoomId).get()
            .addOnSuccessListener { receiver->

                if (receiver.exists()){
                    val adminId = receiver["receiverId"]
                    if (adminId != null) {
                        dbRefAdmin.document(adminId as String)
                            .addSnapshotListener { value, _ ->
                                admin.postValue(value?.toObject())

                            }
                    }
                }
            }



        return admin
    }

    fun deleteMessage(messagingId: String) {
        dbRef.document("Community").collection("dialogs")
            .document(messagingId).delete()
    }

    fun updateMessage(message: MessageEntity){
        dbRef.document("Community").collection("dialogs")
            .document(message.messagingId!!)
            .set(message)
    }


//    fun sendNotification(userName: String?, sessionUId: String?, param1: String?) {
//        val dbRef = Firebase.firestore
//
//        dbRef.collection("Admin")
//            .get()
//            .addOnSuccessListener {
//                if (!it.isEmpty){
//                    for (admin in it){
//                        dbRef.collection("Admin")
//                            .document(admin.id)
//                            .collection("Notifications")
//                            .add(GetNotificationsModel(param1,userName,sessionUId))
//                    }
//                }
//            }
//    }

}
