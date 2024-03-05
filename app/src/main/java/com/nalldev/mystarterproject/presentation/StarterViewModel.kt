package com.nalldev.mystarterproject.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nalldev.mystarterproject.data.model.StartedModel
import com.nalldev.mystarterproject.data.repository.StarterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarterViewModel @Inject constructor(
    private val starterRepository: StarterRepository
) : ViewModel() {
    val personsFlow : Flow<PagingData<StartedModel>> = starterRepository.getStarter(1, 20).cachedIn(viewModelScope)

    private val _pagingDataFlow = MutableStateFlow<PagingData<StartedModel>>(PagingData.empty())
    val pagingDataFlow : StateFlow<PagingData<StartedModel>> = _pagingDataFlow.asStateFlow()


    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    init {
        viewModelScope.launch {
            checkLoginStatus()

            starterRepository.getStarter(1, 20).collectLatest { pagingData ->
                _pagingDataFlow.value = pagingData
            }
        }
    }

    private suspend fun checkLoginStatus() {
        _isLoggedIn.value = starterRepository.getIsLoggedIn()
    }

    fun setLoginStatus(isLoggedIn: Boolean) = viewModelScope.launch {
        starterRepository.setIsLoggedIn(isLoggedIn)
        _isLoggedIn.value = isLoggedIn
    }
}