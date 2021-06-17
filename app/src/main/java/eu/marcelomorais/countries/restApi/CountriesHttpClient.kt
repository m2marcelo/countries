package eu.marcelomorais.countries.restApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class CountriesHttpClient: OkHttpClient() {

    companion object {

        val interceptor = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        fun getClient(): OkHttpClient {
            return Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val url = original
                        .url
                        .newBuilder()
                        .build()
                    val request = original
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(request)
                }.addInterceptor(interceptor)
                .build()
        }
    }
}