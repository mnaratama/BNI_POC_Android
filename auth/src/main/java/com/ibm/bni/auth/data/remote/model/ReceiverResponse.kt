package com.ibm.bni.auth.data.remote.model

data class ReceiverResponse (
    var success   : Boolean,
    var Receivers : ArrayList<ReceiverFields> = arrayListOf(),
    var message   : String,
    var error     : String,
    var status    : Int
)