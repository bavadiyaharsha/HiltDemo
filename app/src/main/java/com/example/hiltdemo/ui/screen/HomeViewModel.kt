package com.example.hiltdemo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltdemo.data.Post
import com.example.hiltdemo.domain.usecase.GetObjectUseCase
import com.example.hiltdemo.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getObjectUseCase: GetObjectUseCase
): ViewModel() {

    val _uiState = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val uiState :MutableStateFlow<UiState<List<Post>>> = _uiState



    fun callObjectApi(){
        viewModelScope.launch {
            _uiState.value=UiState.Loading
            try{
                _uiState.value=UiState.Success(getObjectUseCase())
            }catch (err:Exception){
                _uiState.value=UiState.Error(err.message.toString())
            }
        }

    }
}
