package com.sandeepsharma_kgp.kitabelitask

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sandeepsharma_kgp.kitabelitask.databinding.PurchaseItemBinding


class ItemListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ResponseModel, ItemListAdapter.ItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ResponseModel>() {
        override fun areItemsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResponseModel, newItem: ResponseModel): Boolean {
            return oldItem.itemId == newItem.itemId
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            PurchaseItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.groupButton.setOnClickListener {
            onClickListener.onClick(item.itemDTO)
        }
        holder.bind(item)
    }

    class ItemViewHolder(var binding: PurchaseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseModel) {
            binding.response = item
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (itemDetail: ItemDTO) -> Unit) {
        fun onClick(itemDetail: ItemDTO) = clickListener(itemDetail)
    }
}