package com.learning.epstask.ui.main.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.epstask.R
import com.learning.epstask.common.dialogs.CustomAlertDialog
import com.learning.epstask.common.extensions.requireActivity
import com.learning.epstask.common.extensions.setActionBarTitle
import com.learning.epstask.common.extensions.toastySuccess
import com.learning.epstask.common.utils.Status
import com.learning.epstask.data.remote.model.Orders
import com.learning.epstask.databinding.ActivityMainBinding
import com.learning.epstask.ui.main.adapter.MainAdapter
import com.learning.epstask.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter

    private val myOrders: MutableList<Orders.Item> by lazy { ArrayList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupListener()
        setupUI()
        setupShopObserver()
        setupOrderObserver()
    }

    private fun setupListener() {

        binding.apply {
            btnC8.setOnClickListener {

                btnC8.setBackgroundResource(R.drawable.button_bg_fill_green)
                btnC8.setTextColor(Color.WHITE)

                btnPd2.setBackgroundResource(R.drawable.button_bg_stroke_yellow)
                btnPd2.setTextColor(Color.parseColor("#FFC107"))

                btnDl4.setBackgroundResource(R.drawable.button_bg_stroke_red)
                btnDl4.setTextColor(Color.parseColor("#770A02"))


                val filteredList = myOrders.filter { //C8
                    it.statusID == 12
                }
                renderOrders(filteredList as MutableList<Orders.Item>)

                toastySuccess("${filteredList.size} C-8 Orders")

            }
            btnPd2.setOnClickListener { //PD2

                btnC8.setBackgroundResource(R.drawable.button_bg_stroke_green)
                btnC8.setTextColor(Color.parseColor("#009688"))

                btnPd2.setBackgroundResource(R.drawable.button_bg_fill_yellow)
                btnPd2.setTextColor(Color.WHITE)

                btnDl4.setBackgroundResource(R.drawable.button_bg_stroke_red)
                btnDl4.setTextColor(Color.parseColor("#770A02"))


                val filteredList = myOrders.filter {
                    it.statusID == 9
                }
                renderOrders(filteredList as MutableList<Orders.Item>)

                toastySuccess("${filteredList.size} PD-2 Orders")
            }

            btnDl4.setOnClickListener { //DL4

                btnC8.setBackgroundResource(R.drawable.button_bg_stroke_green)
                btnC8.setTextColor(Color.parseColor("#009688"))

                btnPd2.setBackgroundResource(R.drawable.button_bg_stroke_yellow)
                btnPd2.setTextColor(Color.parseColor("#FFC107"))

                btnDl4.setBackgroundResource(R.drawable.button_bg_fill_red)
                btnDl4.setTextColor(Color.WHITE)

                val filteredList = myOrders.filter {
                    it.statusID == 15
                }
                renderOrders(filteredList as MutableList<Orders.Item>)

                toastySuccess("${filteredList.size} DL-4 Orders")
            }


        }


        binding.search.queryHint = "Search something.."
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterItems(newText)
                return false
            }
        })
    }

    private fun setupUI() {
        setActionBarTitle("Shop Profile")
        binding.rvOrderItem.layoutManager = LinearLayoutManager(requireActivity())
        adapter = MainAdapter(arrayListOf())
        binding.rvOrderItem.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun setupShopObserver() {
        mainViewModel.shopData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        binding.tvShopTitle.text = data.shopName
                        binding.tvShpAddress.text = "${data.district}, ${data.thana}"
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    CustomAlertDialog.showError(this@MainActivity, it.message)
                }
            }
        }
    }

    private fun setupOrderObserver() {
        mainViewModel.orders.observe(this) { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    // progressBar.visibility = View.GONE
                    it.data?.let { orders ->
                        renderOrders(orders)
                        myOrders.addAll(orders)

                    }
                    // binding.rvUser.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    // progressBar.visibility = View.VISIBLE
                    // binding.rvUser.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    // progressBar.visibility = View.GONE
                    //  toastyError(it.message)
                    CustomAlertDialog.showError(this@MainActivity, it.message)
                }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun renderOrders(orders: MutableList<Orders.Item>) {
        adapter.submitData(orders)
    }


    fun filterItems(searchItem: String) {
        Timber.e("TEST: $searchItem")
        if (myOrders.isNotEmpty()) {
            val temItems: MutableList<Orders.Item> = java.util.ArrayList()
            for (item in myOrders) {
                if (item.bookingID.toString().lowercase(Locale.getDefault()).contains(
                        searchItem.lowercase(
                            Locale.getDefault()
                        )
                    )
                    || item.dueAmount.toString().contains(searchItem) || item.paidAmount.toString()
                        .lowercase(Locale.getDefault())
                        .contains(searchItem.lowercase(Locale.getDefault()))
                ) {
                    temItems.add(item)
                }
            }
            renderOrders(temItems)
            // binding.tvCount.setText("Total: " + items.size)
        }
        if (searchItem.isEmpty()) {
            adapter.submitData(myOrders)
            // binding.tvCount.setText("Total: " + items.size)
        }
    }

}

