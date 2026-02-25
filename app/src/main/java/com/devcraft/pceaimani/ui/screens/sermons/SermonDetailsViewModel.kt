package com.devcraft.pceaimani.ui.screens.sermons


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcraft.pceaimani.data.model.Sermon
import com.devcraft.pceaimani.data.repository.SermonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SermonDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: SermonRepository = SermonRepository()
) : ViewModel() {

    private val sermonId: String = checkNotNull(savedStateHandle["sermonId"])

    private val _sermon = MutableStateFlow<Sermon?>(null)
    val sermon: StateFlow<Sermon?> = _sermon.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadSermon()
    }

    private fun loadSermon() {
        viewModelScope.launch {
            repository.getSermonById(sermonId)
                .catch { e ->
                    _error.value = e.localizedMessage ?: "Failed to load sermon"
                    _isLoading.value = false
                }
                .collect { loadedSermon ->
                    _sermon.value = loadedSermon
                    _isLoading.value = false
                    _error.value = null
                }
        }
    }

    fun retry() {
        _isLoading.value = true
        _error.value = null
        loadSermon()
    }
}