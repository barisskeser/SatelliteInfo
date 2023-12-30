package com.baris.core.extensions

import com.baris.core.IResult
import com.baris.core.ResultError


/**
 * Created on 29.12.2023.
 * @author Barış Keser
 */

inline fun <T> IResult<T>.onLoading(body: () -> Unit) = this.also {
    if (this is IResult.Loading)
        body.invoke()
}

inline fun <T> IResult<T>.onSuccess(body: (T) -> Unit) = this.also {
    if (this is IResult.Success)
        body.invoke(this.data)
}

inline fun <T> IResult<T>.onError(body: (ResultError) -> Unit) = this.also {
    if (this is IResult.Error)
        body.invoke(this.error)
}