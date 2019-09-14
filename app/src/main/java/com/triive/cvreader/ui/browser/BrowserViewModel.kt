package com.triive.cvreader.ui.browser

import android.view.View
import com.triive.cvreader.model.Resume
import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.navigation.NavRequest
import com.triive.cvreader.ui.BaseViewModel
import com.triive.cvreader.utils.SafeLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class BrowserViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher), SafeLiveData.LiveDataContainer {

    val resumes: SafeLiveData<List<Resume>> = SafeLiveData()

    fun fetchResumes() {
        if (resumes.value == null) {
            resumes.postValue(Resume.mockedResumes)
        }
    }

    fun onResumeClick(resume: Resume, transactionViews: List<View>?) = launch {
        navDispatcher.navigate(NavRequest.Main.ResumeDetails(resume), transactionViews)
    }
}
