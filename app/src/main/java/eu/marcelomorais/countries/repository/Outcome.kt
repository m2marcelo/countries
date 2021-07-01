package eu.marcelomorais.countries.repository

sealed class Outcome<out T> {
    data class Success<T>(val value: T) : Outcome<T>()
    data class Error<T>(val cause: Exception? = null) : Outcome<T>()
    class Loading<T>() : Outcome<T>()
}