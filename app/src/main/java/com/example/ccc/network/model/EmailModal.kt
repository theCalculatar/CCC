package com.example.ccc.network.model

class EmailModal(
    val name:String,
    val email:String,
    val reqest_type:String,
    val serial_numer:String?=null,
    val device_name:String?=null,
    val phone_number:String?=null,
) {
}