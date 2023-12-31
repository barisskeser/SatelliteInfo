package com.baris.feature.satellites

import androidx.lifecycle.viewModelScope
import com.baris.core.BaseViewModel
import com.baris.core.ResultError
import com.baris.core.UiEvent
import com.baris.core.UiState
import com.baris.core.extensions.onError
import com.baris.core.extensions.onLoading
import com.baris.core.extensions.onSuccess
import com.baris.domain.model.Satellite
import com.baris.domain.usecase.GetSatelliteListUseCase
import com.baris.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val getSatelliteListUseCase: GetSatelliteListUseCase,
    private val searchUseCase: SearchUseCase
) : BaseViewModel<SatellitesViewModel.State, SatellitesViewModel.Event>() {


    private var searchJob: Job? = null
    private var lastSearchQuery: String = ""

    companion object {
        const val SEARCH_DELAY = 1500L
    }

    init {
        getSatellites()
    }

    override fun initialState(): State {
        return State.Empty
    }

    private fun getSatellites() {
        viewModelScope.launch {
            getSatelliteListUseCase().collect {
                it.onLoading {
                    updateUiState(State.Loading)
                }.onSuccess { satelliteList ->
                    updateUiState(State.Success(satelliteList))
                }.onError { resultError ->
                    sendUiEvent(Event.ShowError(resultError))
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        if (query.length >= 2 && lastSearchQuery != query) {
            searchJob?.cancel()
            searchJob = search(query)
        } else if (query.length < 2) {
            searchJob?.cancel()
            getSatellites()
        }
    }

    private fun search(query: String) = viewModelScope.launch {
        updateUiState(State.Loading)
        delay(SEARCH_DELAY)
        searchUseCase(query).collect {
            it.onSuccess { searchResult ->
                lastSearchQuery = query
                updateUiState(State.Success(searchResult))
            }.onError { resultError ->
                sendUiEvent(Event.ShowError(resultError))
            }
        }
    }

    sealed interface State : UiState {
        data object Loading : State
        data class Success(val data: List<Satellite>) : State
        data object Empty : State
    }

    sealed interface Event : UiEvent {
        data object Init : Event
        data class ShowError(val resultError: ResultError) : Event
    }

}