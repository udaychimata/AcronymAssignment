package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _uiState = MutableLiveData<Result<List<ResponseItem>>>()
    val uiState: LiveData<Result<List<ResponseItem>>>
        get() = _uiState

    var isLoading = false


    fun getAcronymList(query: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                val result = repository.getSearchResult(query)
                _uiState.value = Result.success(result)
                isLoading = false
            } catch (exception: Exception) {
                _uiState.value = Result.failure(exception)
                isLoading = false
            }
        }
    }
}