package com.hy.donelist.ui

import android.content.Context
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
import com.hy.donelist.data.DoneListDataStore
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.EmptyCoroutineContext

class DoneListViewModel(
    private val doneListDao: DoneListDao,
    private val doneListDataStore: DoneListDataStore
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserData())
    val uiState: StateFlow<UserData> = _uiState.asStateFlow()

    val allItems: LiveData<List<DoneListData>> = doneListDao.getItems().asLiveData()

    /**
     * set doneListDataStore - is_first_execute setting to [value]
     */
    fun setInitFromDoneListDataStore(context: Context, value: Int) {
        CoroutineScope(EmptyCoroutineContext).launch {
            doneListDataStore.saveCurrentSavedCountNumber(value, context)
        }
    }
    /**
     * set current content [content] and check if there is saved content [content]
     */
    fun refreshCurrentContent(content: DoneListData) = viewModelScope.launch {
        val savingContent = doneListDao.getItem(content.date) ?: content

        _uiState.update { currentState ->
            currentState.copy(
                currentContent = savingContent
            )
        }
    }

    /**
     * Delete content from bata base
     */
    fun deleteContent(content: DoneListData) = viewModelScope.launch {
        doneListDao.delete(content)
    }

    /**
     * Set the quantity [countNumber] of done list for creating.
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

class DoneListViewModelFactory(
    private val itemDao: DoneListDao,
    private val doneListDataStore: DoneListDataStore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoneListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DoneListViewModel(itemDao, doneListDataStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}