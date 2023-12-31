package com.baris.domain.usecase

import com.baris.common.Constants
import com.baris.core.IResult
import com.baris.core.ResultError
import com.baris.domain.model.Satellite
import com.baris.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created on 30.12.2023.
 * @author Barış Keser
 */
class GetSatelliteListUseCase @Inject constructor(
    private val repository: SatelliteRepository
) {
    operator fun invoke(): Flow<IResult<List<Satellite>>> = flow {
        emit(IResult.Loading)
        val satellites = repository.getSatelliteList()
        emit(IResult.Success(satellites))
    }.catch {
        emit(IResult.Error(ResultError(it.localizedMessage ?: Constants.DEFAULT_ERROR_MESSAGE)))
    }

}