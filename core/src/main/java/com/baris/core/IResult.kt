package com.baris.core

/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */
sealed interface IResult<out T> {
    data object Loading : IResult<Nothing>
    data class Success<T>(val data: T) : IResult<T>
    data class Error(val error: ResultError) : IResult<Nothing>
}