package com.elmacbeto.composeapp.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elmacbeto.composeapp.data.datasource.Resource
import com.elmacbeto.composeapp.data.model.AllDogModel
import com.elmacbeto.composeapp.data.model.DogModel
import com.elmacbeto.composeapp.domain.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dogRepository: DogRepository
):ViewModel() {

    private val _allDogsLiveData = MutableLiveData<Resource<AllDogModel>>()
    val allDogsLiveData: LiveData<Resource<AllDogModel>> = _allDogsLiveData

    private val _searchText = MutableLiveData<String>()
    val searchText: LiveData<String> = _searchText

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog:LiveData<Boolean> = _showDialog


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onSummit(breed: String) {
        viewModelScope.launch {
            _allDogsLiveData.value = Resource.loading()
            _allDogsLiveData.value = dogRepository.getDogByBreed(breed)
        }
    }

    fun saveFavorite(dog: DogModel) {
        _showDialog.value = false
        viewModelScope.launch {
            dogRepository.saveFavorite(dog)
        }
    }
}