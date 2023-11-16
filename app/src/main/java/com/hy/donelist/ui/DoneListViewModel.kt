package com.hy.donelist.ui

import androidx.lifecycle.ViewModel
import com.hy.donelist.data.DoneListData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import com.hy.donelist.data.UserData
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DoneListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UserData())
    val uiState: StateFlow<UserData> = _uiState.asStateFlow()

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

    fun addDoneListData(newDoneData: DoneListData) {
        val addedDoneList = _uiState.value.doneList + newDoneData
        _uiState.update { currentState ->
            currentState.copy(
                doneList = addedDoneList
            )
        }
    }
}