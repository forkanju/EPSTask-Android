package com.learning.epstask.data.remote.api

import com.learning.epstask.data.remote.model.Orders
import com.learning.epstask.data.remote.model.Shop
import retrofit2.Response

interface ApiHelper {
    suspend fun getShopDetails(body: Map<String, String>): Response<Shop>

    suspend fun getOrders(body: Map<String, String>): Response<Orders>
}