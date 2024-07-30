package com.example.ccc.model

class ReportModel(
    var display: String,
    var type: String,
    var userId: String,
    var dropOff: String,
    var userName: String,
    var number: String,
    var postId: String? = null
) {
    constructor():this("","","","","","")
}