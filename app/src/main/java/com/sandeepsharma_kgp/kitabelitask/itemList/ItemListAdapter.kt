package com.sandeepsharma_kgp.kitabelitask.itemList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sandeepsharma_kgp.kitabelitask.ItemDTO
import com.sandeepsharma_kgp.kitabelitask.ResponseModel
import com.sandeepsharma_kgp.kitabelitask.databinding.PurchaseItemBinding
import java.sql.Timestamp


class ItemListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ResponseModel, ItemListAdapter.ItemViewHolder>(
        DiffCallback
    ) {

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
        val button = holder.binding.groupButton
        val currentEpoch = Timestamp(System.currentTimeMillis())
        val expiryEpoch = item.expireAt?.toLong()?.times(1000)
        val millis = expiryEpoch?.minus(currentEpoch.time)
        if (millis!=null && millis < 0) {
            button.text = "EXPIRED"
            button.setTextColor(Color.BLACK)
            button.setBackgroundColor(Color.GRAY)
            button.isClickable = false
        } else {
            var text = "BUY WITH ${item.userNames.get(0)}"
            val size = item.userNames.size
            if (size > 1)
                text += " AND ${size - 1} OTHER"

            button.text = text
            button.setTextColor(Color.WHITE)
            button.setBackgroundColor(0xff669900.toInt())
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