package com.learning.epstask.data.remote.repository

import com.learning.epstask.data.remote.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getShopDetails(body: Map<String, String>) = apiHelper.getShopDetails(body)

    suspend fun getOrders(body: Map<String, String>) = apiHelper.getOrders(body)
}