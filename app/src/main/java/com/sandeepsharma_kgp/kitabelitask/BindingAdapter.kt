package com.sandeepsharma_kgp.kitabelitask

import android.graphics.Paint
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ResponseModel>?) {
    val adapter = recyclerView.adapter as ItemListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgSrcUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        var imgUrl = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("itemPrice")
fun bindPriceText(textView: TextView, itemPrice: String?) {
    textView.text = "Rp $itemPrice"
    textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("discountPrice")
fun bindDiscountPriceText(textView: TextView, discountPrice: String?) {
    textView.text = "Rp $discountPrice"
}

@BindingAdapter("buttonText")
fun bindDiscountPriceText(button: Button, buttonText: List<String>?) {
    var text = "BUY WITH ${buttonText?.get(0)}"
    val size = buttonText?.size!!
    if(size > 1)
        text += " AND ${size-1} OTHER"

    button.text = text
}
