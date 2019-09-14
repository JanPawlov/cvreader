package com.triive.cvreader.ui.details

import com.triive.cvreader.model.Resume
import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class ResumeDetailsViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher,
    private val resume: Resume
) : BaseViewModel(backgroundDispatcher) {

    fun navigateBack() = navDispatcher.navigateBack(this)
}
