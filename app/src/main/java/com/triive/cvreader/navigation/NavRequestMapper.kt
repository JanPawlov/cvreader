package com.triive.cvreader.navigation

import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.ui.browser.BrowserFragment
import com.triive.cvreader.ui.details.ResumeDetailsFragment

fun NavRequest.toFragmentCreator(): () -> BaseFragment {
    return when (this) {
        NavRequest.Main.Home -> BrowserFragment.creator()
        is NavRequest.Main.ResumeDetails -> ResumeDetailsFragment.creator(resume)
    }
}
