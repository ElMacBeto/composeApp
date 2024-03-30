package com.elmacbeto.composeapp.data.datasource

import com.elmacbeto.composeapp.core.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Resource<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(msg: String? = null, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, message = msg, data = data)
        }

        fun <T> loading(msg: Int? = null): Resource<T> {
            return Resource(Status.LOADING)
        }
    }
}