package com.boulevard.uitestingarticle.core.model

data class Resource<out T>(
    val status: Status,
    val message: String?,
    val data: T?
) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, null, data)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, msg, data)
        }
    }
}
