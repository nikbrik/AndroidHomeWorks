package com.nikbrik.hw19

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepository = ProductRepository()
    private val context get() = getApplication<Application>().applicationContext

    private val productLiveData = MutableLiveData<List<Product>>(
        productRepository.createNewList(context, 100)
    )
    val products: LiveData<List<Product>>
        get() = productLiveData

    private val showToastLiveData = SingleLiveEvent<Int>()
    val showToast: LiveData<Int>
        get() = showToastLiveData

    fun addProduct(title: String, description: String) {
        val newList = productRepository.addProduct(
            productLiveData.value.orEmpty(),
            position = 0,
            title,
            description
        )
        productLiveData.postValue(newList)
    }

    fun removeProduct(position: Int) {
        val newList = productRepository.removeProduct(productLiveData.value.orEmpty(), position)
        productLiveData.postValue(newList)
        showToastLiveData.postValue(position)
    }

    fun loadMore(totalItemsCount: Int) {
        if (totalItemsCount < 5000) {
            val newList = productLiveData.value.orEmpty() + productRepository.createNewList(
                context,
                10
            )
            productLiveData.postValue(newList)
        }
    }
}
