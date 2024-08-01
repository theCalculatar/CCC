package com.example.ccc.ui.chat.model

data class MessageEntity(
    val messagingId:String?,
    val senderId:String,
    val roomId:String,
    val body:String?,
    val time:String?){
    constructor():this("","","","","")
}