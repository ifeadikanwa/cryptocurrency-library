package com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object


import com.google.gson.annotations.SerializedName

data class TeamMember(
    val id: String,
    val name: String,
    val position: String
)