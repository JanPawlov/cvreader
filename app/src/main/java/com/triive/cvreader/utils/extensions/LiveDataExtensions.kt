package com.triive.cvreader.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) = observe(lifecycleOwner::getLifecycle, observer)
