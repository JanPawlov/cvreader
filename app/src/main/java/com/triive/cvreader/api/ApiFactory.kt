package com.triive.cvreader.api

import com.triive.cvreader.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class ApiFactory : KoinComponent {
    private val interceptor: RetrofitInterceptor by inject()
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val baseUrl = "https://google.com"

    fun createApi(): CvReaderAPI = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideHttpClient())
        .build()
        .create(CvReaderAPI::class.java)


    private fun provideHttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .writeTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
        .build()

    companion object {
        private const val TIMEOUT_DEFAULT = 15L
    }
}
