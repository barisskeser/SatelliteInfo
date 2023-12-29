package com.baris.core.extensions

import com.baris.core.IResult
import com.baris.core.ResultError


/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

fun <T> IResult<T>.onLoading(body: () -> Unit) {
    if (this is IResult.Loading)
        body.invoke()
}

fun <T> IResult<T>.onSuccess(body: (T) -> Unit) {
    if (this is IResult.Success)
        body.invoke(this.data)
}

fun <T> IResult<T>.onError(body: (ResultError) -> Unit) {
    if (this is IResult.Error)
        body.invoke(this.error)
}