package com.sandeepsharma_kgp.kitabelitask.itemDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sandeepsharma_kgp.kitabelitask.R
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(context: Context) :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private val context: Context
    private var mSliderItems: MutableList<String> = ArrayList()
    fun renewItems(sliderItems: MutableList<String>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: String) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    fun submitList(sliderItems: List<String>) {
        mSliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout, null)
        return SliderAdapterVH(
            inflate
        )
    }

    override fun onBindViewHolder(
        viewHolder: SliderAdapterVH,
        position: Int
    ) {
        val sliderItem: String = mSliderItems[position]
        Glide.with(viewHolder.itemView)
            .load(sliderItem)
            .into(viewHolder.imageViewBackground)
        viewHolder.itemView.setOnClickListener(object : View.OnClickListener  {
            override fun onClick(v: View?) {
                Toast.makeText(context, "This is item in position $position", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun getCount(): Int { //slider view count could be dynamic size
        return mSliderItems.size
    }

    class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var imageViewBackground: ImageView
        var imageGifContainer: ImageView

        init {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider)
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container)
        }
    }

    init {
        this.context = context
    }
}