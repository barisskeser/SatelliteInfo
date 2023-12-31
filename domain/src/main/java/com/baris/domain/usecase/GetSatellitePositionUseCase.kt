package com.baris.domain.usecase

import android.util.Log
import com.baris.common.Constants
import com.baris.core.IResult
import com.baris.core.ResultError
import com.baris.domain.model.Position
import com.baris.domain.model.SatellitePosition
import com.baris.domain.repository.SatelliteRepository
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
class GetSatellitePositionUseCase @Inject constructor(
    private val repository: SatelliteRepository
) {
    operator fun invoke(id: Int): Flow<IResult<Position?>> = flow {
        emit(IResult.Loading)
        val satellites = repository.getSatellitePosition(id)
        var index = 0
        val bound = satellites?.positions?.size ?: 0
        while (true) {
            val position = satellites?.positions?.getOrNull(index % bound)
            Log.d("TAG", "invoke: $position")
            emit(IResult.Success(position))
            index++
            delay(3000L)
        }
        awaitCancellation()
    }.catch {
        Log.d("TAG", "invoke: $it")
        emit(IResult.Error(ResultError(it.localizedMessage ?: Constants.DEFAULT_ERROR_MESSAGE)))
    }
}