package com.triive.cvreader.ui.browser

import android.view.View
import com.triive.cvreader.api.ApiRepository
import com.triive.cvreader.api.Outcome
import com.triive.cvreader.model.Resume
import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.navigation.NavRequest
import com.triive.cvreader.ui.BaseViewModel
import com.triive.cvreader.utils.SafeLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class BrowserViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher,
    private val apiRepository: ApiRepository
) : BaseViewModel(backgroundDispatcher), SafeLiveData.LiveDataContainer {

    val resumes: SafeLiveData<List<Resume>> = SafeLiveData()
    val fetchResult: SafeLiveData<Boolean> = SafeLiveData()

    fun fetchResumes() = launch {
        if (resumes.value == null) {
            when (val response = apiRepository.fetchResumes()) {
                is Outcome.Success -> {
                    fetchResult.postValue(true)
                    val content = response.data.files.resumesjson.content.resumes
                    resumes.postValue(content)
                }
                else -> {
                    fetchResult.postValue(false)
                }
            }
        }
    }

    fun onResumeClick(resume: Resume, transactionViews: List<View>?) = launch {
        navDispatcher.navigate(NavRequest.Main.ResumeDetails(resume), transactionViews)
    }
}
