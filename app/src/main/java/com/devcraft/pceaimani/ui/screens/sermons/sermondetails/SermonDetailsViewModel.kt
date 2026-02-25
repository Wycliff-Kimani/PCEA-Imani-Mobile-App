package com.devcraft.pceaimani.ui.screens.sermons.sermondetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devcraft.pceaimani.data.model.Sermon
import com.devcraft.pceaimani.data.repository.SermonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SermonDetailsViewModelFactory(private val sermonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SermonDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SermonDetailsViewModel(sermonId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class SermonDetailsViewModel(
    private val sermonId: String,
    private val repository: SermonRepository = SermonRepository()
) : ViewModel() {


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
            fetchSermon(sermonId)
        }
    }
}