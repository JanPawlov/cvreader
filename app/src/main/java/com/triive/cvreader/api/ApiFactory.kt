package com.triive.cvreader.api

import com.squareup.moshi.Moshi
import com.triive.cvreader.BuildConfig
import com.triive.cvreader.api.response.adapter.LocalDateTimeAdapter
import com.triive.cvreader.api.response.adapter.ResumeListAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

    private val baseUrl = "https://api.github.com/"

    fun createApi(): CvReaderAPI = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(provideConverterFactory())
        .client(provideHttpClient())
        .build()
        .create(CvReaderAPI::class.java)

    private fun provideConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(ResumeListAdapter())
            .add(LocalDateTimeAdapter())
            .build()

        return MoshiConverterFactory.create(moshi)
    }

    private fun provideHttpClient() = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .addInterceptor(loggingInterceptor)
        .writeTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
        .build()

    companion object {
        private const val TIMEOUT_DEFAULT = 5L
    }
}
