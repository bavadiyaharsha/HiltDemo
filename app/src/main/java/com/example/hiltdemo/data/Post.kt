package com.example.hiltdemo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("data")
    val phoneData: PhoneData? = null
)

@Serializable
data class PhoneData(
    @SerialName("color")
    val color: String? = null,
    @SerialName("capacity")
    val capacity: String? = null
)
