package com.elmacbeto.composeapp.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class AllDogsResponse (
    @SerializedName("message" ) var images : ArrayList<String> = arrayListOf(),
    @SerializedName("status"  ) var status  : String?           = null
)