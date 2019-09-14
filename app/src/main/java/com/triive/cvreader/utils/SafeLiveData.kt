package com.triive.cvreader.utils

import androidx.lifecycle.LiveData

open class SafeLiveData<T : Any?> : LiveData<T>() {

    interface LiveDataContainer {
        fun <T : Any?> SafeLiveData<T>.postValue(value: T?) {
            postValue(value)
        }

        fun <T : Any?> SafeLiveData<T>.setValue(value: T?) {
            setValue(value)
        }
    }
}
