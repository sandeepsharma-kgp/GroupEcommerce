package com.sandeepsharma_kgp.kitabelitask

import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sandeepsharma_kgp.kitabelitask.itemList.ItemListAdapter
import com.sandeepsharma_kgp.kitabelitask.itemList.ItemListApiStatus
import java.sql.Timestamp
import java.util.concurrent.TimeUnit


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

@BindingAdapter("itemListApiStatus")
fun bindStatus(statusImageView: ImageView, status: ItemListApiStatus?) {
    when (status) {
        ItemListApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_dashboard_black_24dp)
        }
        ItemListApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
        ItemListApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("progressApiStatus")
fun bindProgressStatus(progressBar: ProgressBar, status: ItemListApiStatus?) {
    when (status) {
        ItemListApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        ItemListApiStatus.ERROR -> {
            progressBar.visibility = View.INVISIBLE
        }
        ItemListApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("groupPrice")
fun bindGroupPriceText(textView: TextView, groupPrice: String?) {
    textView.text = "Rp $groupPrice/item"
}

@BindingAdapter("price", "itemCount")
fun bindingCalculatedPrice(textView: TextView, price: Int, itemCount: Int) {
    var text = "Total Price: "
    var value = price * itemCount
    text += value.toString()

    textView.text = text
}

@BindingAdapter("expireAt")
fun bindingExpireAt(textView: TextView, expireAt: Int) {
    val currentEpoch = Timestamp(System.currentTimeMillis())
    val expiryEpoch = expireAt.toLong() * 1000
    val millis = expiryEpoch - currentEpoch.time
    if (millis > 0) {
        val hms = String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    millis
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millis
                )
            )
        )

        textView.text = "Time: " + hms

    } else {
        textView.text = "Expired"
    }

}