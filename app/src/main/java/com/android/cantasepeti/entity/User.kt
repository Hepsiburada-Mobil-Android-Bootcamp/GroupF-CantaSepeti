package com.android.cantasepeti.entity

import java.io.Serializable

data class User(
    val userName:String? = null,
    val email:String? = null,
    val password:String?=null
): Serializable
