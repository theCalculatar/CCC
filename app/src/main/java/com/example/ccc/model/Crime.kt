package com.example.ccc.model

import android.view.Display

class Crime(val type:String,
            val display:String?,
            val description:String?,
            var userId: String,
            var userName: String,
            var postId: String?) {
    constructor():this("","","","","","")
}