package com.learning.epstask.data.remote.model


import com.squareup.moshi.Json


class Orders : ArrayList<Orders.Item>() {
    data class Item(
        @Json(name = "address")
        val address: Any,
        @Json(name = "bookingID")
        val bookingID: Int,
        @Json(name = "cd")
        val cd: String,
        @Json(name = "clientID")
        val clientID: Int,
        @Json(name = "code")
        val code: String,
        @Json(name = "compnayID")
        val compnayID: Int,
        @Json(name = "deliveryDate")
        val deliveryDate: String,
        @Json(name = "dueAmount")
        val dueAmount: Int,
        @Json(name = "paidAmount")
        val paidAmount: Int,
        @Json(name = "products")
        val products: Any,
        @Json(name = "rOuteFK")
        val rOuteFK: Int,
        @Json(name = "remarks")
        val remarks: Any,
        @Json(name = "shopFK")
        val shopFK: Int,
        @Json(name = "shopName")
        val shopName: Any,
        @Json(name = "statusID")
        val statusID: Int,
        @Json(name = "totalDiscount")
        val totalDiscount: Int,
        @Json(name = "totalItem")
        val totalItem: Int,
        @Json(name = "totalValue")
        val totalValue: Double,
        @Json(name = "typeID")
        val typeID: Int,
        @Json(name = "userID")
        val userID: Int,
        @Json(name = "voucherID")
        val voucherID: Int
    )
}