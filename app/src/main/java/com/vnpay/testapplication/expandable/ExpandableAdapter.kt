package com.vnpay.testapplication.expandable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vnpay.testapplication.databinding.ItemExpandableBinding

class ExpandableAdapter(private val items: List<Item>) : RecyclerView.Adapter<ExpandableAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemExpandableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                tvTitle.text = item.title
                tvDes0.text = item.des0
                tvDes1.text = item.des1
                tvDes2.text = item.des2

                // Handle click events or expand/collapse logic here if needed
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExpandableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
