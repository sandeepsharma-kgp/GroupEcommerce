package com.sandeepsharma_kgp.kitabelitask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


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