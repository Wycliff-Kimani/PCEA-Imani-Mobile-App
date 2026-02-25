package com.devcraft.pceaimani.ui.screens.sermons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcraft.pceaimani.data.model.Sermon
import com.devcraft.pceaimani.data.repository.SermonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SermonsViewModel(
    private val repository: SermonRepository = SermonRepository
) : ViewModel() {

    private val _sermons = MutableStateFlow<List<Sermon>>(emptyList())
    val sermons: StateFlow<List<Sermon>> = _sermons.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchSermons()
    }

    fun fetchSermons() {
        _isLoading.value = true     // show loading again on manual refresh/retry
        _error.value = null         // clear previous error

        viewModelScope.launch {
            repository.getSermons()
                .collect { sermonList ->
                    _sermons.value = sermonList
                    _isLoading.value = false
                    _error.value = null
                }
        }
    }
}