package com.learning.epstask.data.remote.model


import com.google.gson.annotations.SerializedName

data class Shop(
    @SerializedName("address")
    val address: Any,
    @SerializedName("code")
    val code: Any,
    @SerializedName("confirm")
    val confirm: Int,
    @SerializedName("date")
    val date: String,
    @SerializedName("delivered")
    val delivered: Int,
    @SerializedName("district")
    val district: String,
    @SerializedName("districtID")
    val districtID: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("orderID")
    val orderID: Int,
    @SerializedName("partialDelivered")
    val partialDelivered: Int,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("shopID")
    val shopID: Int,
    @SerializedName("shopName")
    val shopName: String,
    @SerializedName("statusID")
    val statusID: Int,
    @SerializedName("thana")
    val thana: String,
    @SerializedName("thanaID")
    val thanaID: Int
)