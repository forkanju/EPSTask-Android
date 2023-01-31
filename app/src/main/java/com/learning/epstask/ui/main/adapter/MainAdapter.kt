package com.learning.epstask.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.epstask.data.remote.model.Orders
import com.learning.epstask.databinding.RvOrderItemBinding

class MainAdapter(private val orders: ArrayList<Orders.Item>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(var binding: RvOrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            RvOrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.apply {
            tvOrderId.text = "Order ID# ${order.bookingID}"
            tvDate.text = order.deliveryDate
            tvTk.text = "Tk ${order.totalValue.toInt()}"
            tvTotalItem.text = "Total Item# ${order.totalItem}"
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: MutableList<Orders.Item>) {
        orders.clear()
        orders.addAll(list)
        notifyDataSetChanged()
    }

}