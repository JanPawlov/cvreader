package com.triive.cvreader.ui.details

import android.view.View
import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.ui.BaseViewModel
import com.triive.cvreader.utils.SafeLiveData
import kotlinx.coroutines.CoroutineDispatcher

class ResumeDetailsViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher), SafeLiveData.LiveDataContainer {

    fun navigateBack(transitionsViews: List<View>? = null) = navDispatcher.navigateBack(this, transitionsViews)
}
