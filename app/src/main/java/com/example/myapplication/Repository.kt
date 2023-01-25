package com.example.myapplication

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: Service, private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getSearchResult(query: String): List<ResponseItem> {
        return withContext(ioDispatcher) {
            service.searchAcronym(query)
        }
    }
}