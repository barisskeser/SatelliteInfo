package com.baris.feature.satellitedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.baris.common.Params
import com.baris.core.BaseViewModel
import com.baris.core.IResult
import com.baris.core.ResultError
import com.baris.core.UiEvent
import com.baris.core.UiState
import com.baris.domain.usecase.GetSatelliteDetailUseCase
import com.baris.domain.usecase.GetSatellitePositionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase,
    private val getSatellitePositionUseCase: GetSatellitePositionUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SatelliteDetailViewModel.State, SatelliteDetailViewModel.Event>() {
    init {
        val id: Int? = savedStateHandle[Params.SATELLITE_ID]
        val name: String = savedStateHandle[Params.SATELLITE_NAME] ?: ""
        id?.let {
            getSatelliteDetail(id, name)
        }
    }

    override fun initialState(): State {
        return State.Empty
    }

    private fun getSatelliteDetail(id: Int, name: String) {
        viewModelScope.launch {
            val satelliteDetailResult = getSatelliteDetailUseCase(id)
            val positionsResult = getSatellitePositionUseCase(id)

            satelliteDetailResult.combine(positionsResult) { satelliteDetail, positions ->
                when {
                    satelliteDetail is IResult.Loading || positions is IResult.Loading -> updateUiState(
                        State.Loading
                    )

                    satelliteDetail is IResult.Error -> sendUiEvent(Event.ShowError(satelliteDetail.error))
                    positions is IResult.Error -> sendUiEvent(Event.ShowError(positions.error))
                    satelliteDetail is IResult.Success && positions is IResult.Success -> {
                        val detail = satelliteDetail.data
                        val position = positions.data
                        if (detail != null && position != null) {
                            updateUiState(
                                State.Success(
                                    SatelliteDetailUiModel(
                                        id = detail.id,
                                        name = name,
                                        costPerLaunch = detail.costPerLaunch,
                                        firstFlight = detail.firstFlight,
                                        height = detail.height,
                                        mass = detail.mass,
                                        posX = position.posX,
                                        posY = position.posY
                                    )
                                )
                            )
                        }
                    }
                }
            }.collect()
        }
    }

    sealed interface State : UiState {
        data object Loading : State
        data class Success(val data: SatelliteDetailUiModel) : State
        data object Empty : State
    }

    sealed interface Event : UiEvent {
        data object Init : Event
        data class ShowError(val resultError: ResultError) : Event
    }


}