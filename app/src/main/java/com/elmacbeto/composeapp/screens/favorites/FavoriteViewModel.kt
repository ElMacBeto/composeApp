package com.elmacbeto.composeapp.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmacbeto.composeapp.data.datasource.Resource
import com.elmacbeto.composeapp.data.model.DogModel
import com.elmacbeto.composeapp.domain.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    val uiState: StateFlow<Resource<List<DogModel>>> = dogRepository.favorites
        .map { Resource.success(it) }
        .catch { Resource.error(it.message, null) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Resource.loading())

    fun deleteFavorite(dog: DogModel) {
        viewModelScope.launch {
            dogRepository.delete(dog)
        }
    }
}