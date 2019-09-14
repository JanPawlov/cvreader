package com.triive.cvreader.ui.browser

import com.triive.cvreader.model.Resume
import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.navigation.NavRequest
import com.triive.cvreader.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class BrowserViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher) {

    fun onResumeClick(resume: Resume) = launch {
        navDispatcher.navigate(NavRequest.Main.ResumeDetails(resume))
    }
}
