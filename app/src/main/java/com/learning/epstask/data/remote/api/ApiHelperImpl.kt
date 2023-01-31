package com.learning.epstask.data.remote.api

import com.learning.epstask.data.remote.model.Orders
import com.learning.epstask.data.remote.model.Shop
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getShopDetails(body: Map<String, String>): Response<Shop> = apiService.getShopDetails(body)

    override suspend fun getOrders(body: Map<String, String>): Response<Orders> = apiService.getOrders(body)

}