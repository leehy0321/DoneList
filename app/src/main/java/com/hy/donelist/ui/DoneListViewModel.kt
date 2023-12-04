package com.hy.donelist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hy.donelist.data.DoneListDao
import com.hy.donelist.data.DoneListData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import com.hy.donelist.data.UserData
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import androidx.lifecycle.asLiveData

class DoneListViewModel(private val doneListDao: DoneListDao) : ViewModel() {
    private val _uiState = MutableStateFlow(UserData())
    val uiState: StateFlow<UserData> = _uiState.asStateFlow()

    val allItems: LiveData<List<DoneListData>> = doneListDao.getItems().asLiveData()

    /**
     * Set the current content [content] to control (create or show)
     */
    fun setCurrentContents(content: DoneListData) {
        _uiState.update { currentState ->
            currentState.copy(
                currentContent = content
            )
        }
    }

    /**
     * Set the quantity [countNumber] of donelist for creating.
     */
    fun setCountNumber(countNumber: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                numberCount = countNumber
            )
        }
    }

    private fun insertDoneListData(newDoneData: DoneListData) {
        viewModelScope.launch {
            doneListDao.insert(newDoneData)
        }
    }

    fun addDoneListData(data: DoneListData) {
        insertDoneListData(data)
    }
}

class DoneListViewModelFactory(private val itemDao: DoneListDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoneListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DoneListViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}