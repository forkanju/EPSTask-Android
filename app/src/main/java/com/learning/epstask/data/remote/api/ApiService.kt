package com.learning.epstask.data.remote.api

import com.learning.epstask.common.utils.Constants.Companion.ORDERS_ENDPOINT
import com.learning.epstask.common.utils.Constants.Companion.SHOP_DETAILS_ENDPOINT
import com.learning.epstask.data.remote.model.Orders
import com.learning.epstask.data.remote.model.Shop
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(SHOP_DETAILS_ENDPOINT)
    suspend fun getShopDetails(@Body body: Map<String, String>): Response<Shop>

    @POST(ORDERS_ENDPOINT)
    suspend fun getOrders(@Body body: Map<String, String>): Response<Orders>
}