package com.example.br_search_jetpack

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRespository: UserRespository
): ViewModel() {

    var isLoading = mutableStateOf(false)
    private var _getUserData = MutableStateFlow<List<UserResponse>>(emptyList())
    var getUserData  = _getUserData

    suspend fun getUserData(): Resource<List<UserResponse>> {
        val result = userRespository.getUserResponse()
        if (result is Resource.Success) {
            isLoading.value = true
            _getUserData.value = result.data!!
        }

        return result
    }
}