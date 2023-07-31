package com.vimal.kinzooandroidchallenge.util

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading(val progress: Int? = null) : Resource<Nothing>()
    data class Error(val message: String) : Resource<Nothing>()
}