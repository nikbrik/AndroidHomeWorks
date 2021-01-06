package com.nikbrik.generics

sealed class Result<out T,R>{
    data class Success<T, R>(val element:T):Result<T,R>()
    data class Error<T,R>(val element:R):Result<T,R>()
}