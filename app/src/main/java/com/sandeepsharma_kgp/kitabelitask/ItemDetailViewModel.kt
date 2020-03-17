package com.sandeepsharma_kgp.kitabelitask

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandeepsharma_kgp.kitabelitask.ItemDTO
import com.travijuu.numberpicker.library.NumberPicker


class ItemDetailViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<ItemDTO>()
    val selectedItem: LiveData<ItemDTO>
        get() = _selectedItem

    private val _itemUrl = MutableLiveData<String>()
    val itemUrl: LiveData<String>
        get() = _itemUrl

    fun setItem(itemDetail: ItemDTO) {
        _selectedItem.value = itemDetail
    }

    fun shareProduct() {
        _itemUrl.value = selectedItem.value?.description?.tokopedia_url
    }
}