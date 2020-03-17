package com.sandeepsharma_kgp.kitabelitask.itemList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandeepsharma_kgp.kitabelitask.DataSourceApi
import com.sandeepsharma_kgp.kitabelitask.ItemDTO
import com.sandeepsharma_kgp.kitabelitask.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class ItemListApiStatus {
    LOADING, ERROR, DONE
}

class ItemListViewModel : ViewModel() {
    private val _status = MutableLiveData<ItemListApiStatus>()
    val status: LiveData<ItemListApiStatus>
        get() = _status

    private val _navigateToSelectedItem = MutableLiveData<ItemDTO>()
    val navigateToSelectedItem: LiveData<ItemDTO>
        get() = _navigateToSelectedItem

    init {
        getData()
    }

    private lateinit var viewModelJob: Job
    private val _data = MutableLiveData<List<ResponseModel>>()
    val data: LiveData<List<ResponseModel>>
        get() = _data

    private fun getData() {
        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
            var data = DataSourceApi.retrofitService.getData()
            try {
                _status.value =
                    ItemListApiStatus.LOADING
                var listData = data.await()
                _data.value = listData.payload
                _status.value =
                    ItemListApiStatus.DONE
            } catch (t: Throwable) {
                Log.e("ERROR", t.toString())
                _status.value =
                    ItemListApiStatus.ERROR
            }
        }

    }

    fun displayDetail(itemDetail: ItemDTO) {
        _navigateToSelectedItem.value = itemDetail
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}