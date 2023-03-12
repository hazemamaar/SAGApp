package com.example.sagapp.ble.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sagapp.R
import com.example.sagapp.ble.domin.BluetoothDevice

class BluetoothListAdapter : RecyclerView.Adapter<BluetoothListAdapter.BluetoothViewHolder>() {

    inner class BluetoothViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var bleDevices: List<BluetoothDevice>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    private val differCallBack = object : DiffUtil.ItemCallback<BluetoothDevice>() {
        override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: BluetoothDevice,
            newItem: BluetoothDevice,
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothViewHolder {
        return BluetoothViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bluetooth,
                parent,
                false
            )
        )
    }

    private var onItemClickListener: ((BluetoothDevice) -> Unit)? = null

    override fun onBindViewHolder(holder: BluetoothViewHolder, position: Int) {
        val bleDevice = bleDevices[position]
        holder.itemView.apply {


        }
    }

    override fun getItemCount() = bleDevices.size

    fun setOnItemClickListener(listener: (BluetoothDevice) -> Unit) {
        onItemClickListener = listener
    }
}