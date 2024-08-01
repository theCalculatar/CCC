package com.example.ccc.ui.chat.model

import android.os.Parcelable

import kotlinx.parcelize.Parcelize

@Parcelize
data class PreviousChatsEntity(
    val chatRoomId:String,
    val name:String,
    val messagePrev:String?,
    val time:String,
    val displayPic: String
):Parcelable