package com.triive.cvreader.extensions

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.triive.cvreader.api.Outcome
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlin.coroutines.resume

suspend inline fun <T, reified E> Call<T>.awaitOutcome(): Outcome<T, E> {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) = with(response) {
                try {
                    @Suppress("UNCHECKED_CAST")
                    when {
                        isSuccessful -> continuation.resume(Outcome.Success(body() as T, headers()))
                        errorBody() != null -> continuation.resume(Outcome.Failure(errorBody()!!.toModel(), code()))
                        else -> continuation.resume(Outcome.Error(IllegalStateException("${code()}${message()}")))
                    }
                } catch (e: ClassCastException) {
                    Timber.e(e)
                    continuation.resume(Outcome.Error(e))
                } catch (e: JsonDataException) {
                    Timber.e(e)
                    continuation.resume(Outcome.Error(e))
                } catch (e: IllegalStateException) {
                    Timber.e(e)
                    continuation.resume(Outcome.Error(e))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resume(Outcome.Error(t))
            }
        })
    }
}

inline fun <reified E> ResponseBody.toModel(): E {
    val adapter: JsonAdapter<E> = Moshi.Builder().build().adapter(E::class.java)
    return adapter.fromJson(this.string())!!
}
