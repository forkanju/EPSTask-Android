package com.learning.epstask.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.epstask.common.utils.NetworkHelper
import com.learning.epstask.common.utils.Resource
import com.learning.epstask.data.remote.model.Orders
import com.learning.epstask.data.remote.model.Shop
import com.learning.epstask.data.remote.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private val _shopData = MutableLiveData<Resource<Shop>>()

    val shopData: LiveData<Resource<Shop>>
        get() = _shopData


    private val _orders = MutableLiveData<Resource<Orders>>()

    val orders: LiveData<Resource<Orders>>
        get() = _orders


    init {
        val bodyShop = mapOf(
            "UserID" to "120",
            "CompanyID" to "29",
            "ShopFK" to "8",
        )

        val bodyOrder = mapOf(
            "UserID" to "120",
            "CompanyID" to "29",
            "ShopFK" to "8",
            "StatusId" to "0",
        )



        fetchShopDetails(bodyShop)
        fetchOrders(bodyOrder)

    }



    private fun fetchShopDetails(body: Map<String, String>) {
        viewModelScope.launch {
            _shopData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getShopDetails(body).let {
                    if (it.isSuccessful) {
                        _shopData.postValue(Resource.success(it.body()))
                    } else _shopData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _shopData.postValue(Resource.error("No internet connection!", null))
        }
    }


    private fun fetchOrders(body: Map<String, String>) {
        viewModelScope.launch {
            _orders.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getOrders(body).let {
                    if (it.isSuccessful) {
                        _orders.postValue(Resource.success(it.body()))
                    } else _orders.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _orders.postValue(Resource.error("No internet connection!", null))
        }
    }
}