package com.test.strangerthings.repository

import com.test.strangerthings.api.RetrofitInstance
import com.test.strangerthings.model.QuotesItem
import retrofit2.Response

class Repository {

    suspend fun getQuotes(): Response<List<QuotesItem>> {
        return RetrofitInstance.api.getQuotes()
    }
}