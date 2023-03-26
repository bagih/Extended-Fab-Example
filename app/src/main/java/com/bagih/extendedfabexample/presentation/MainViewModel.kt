package com.bagih.extendedfabexample.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    private val _isLikeButtonPressed = MutableStateFlow<Boolean>(false)
    val isLikeButtonPressed = _isLikeButtonPressed.asStateFlow()

    private val _isFavButtonPressed = MutableStateFlow<Boolean>(false)
    val isFavButtonPressed = _isFavButtonPressed.asStateFlow()

    fun likeButtonClicked(){
        _isLikeButtonPressed.value = !_isLikeButtonPressed.value
    }

    fun FavButtonClicked(){
        _isFavButtonPressed.value = !_isFavButtonPressed.value
    }
}