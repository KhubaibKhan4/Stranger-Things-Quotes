package com.test.strangerthings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.strangerthings.model.QuotesItem
import com.test.strangerthings.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Response<List<QuotesItem>>> = MutableLiveData()

    fun getQuotes() {
        viewModelScope.launch {
            val response: Response<List<QuotesItem>> = repository.getQuotes()
            myResponse.value = response
        }
    }
}