package com.devcraft.pceaimani.ui.screens.sermons

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcraft.pceaimani.data.model.Sermon
import com.devcraft.pceaimani.data.repository.SermonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SermonDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: SermonRepository = SermonRepository()
) : ViewModel() {

    // Add this property
    private val sermonId: String = savedStateHandle.get<String>("sermonId") ?: ""

    private val _sermon = MutableStateFlow<Sermon?>(null)
    val sermon: StateFlow<Sermon?> = _sermon.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        if (sermonId.isBlank()) {
            _error.value = "Sermon ID not provided."
            _isLoading.value = false
        } else {
            fetchSermon(sermonId)
        }
    }

    private fun fetchSermon(sermonId: String) {
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                repository.getSermonById(sermonId)
                    .collect { sermon ->
                        _sermon.value = sermon
                        _isLoading.value = false
                        if (sermon == null) {
                            _error.value = "Sermon not found."
                        }
                    }
            } catch (e: Exception) {
                _error.value = "Failed to load: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun retry() {
        if (sermonId.isNotBlank()) {
            fetchSermon(sermonId)  // now it works — sermonId is a property
        }
    }
}