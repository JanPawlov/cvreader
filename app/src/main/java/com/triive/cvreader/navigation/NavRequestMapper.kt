package com.triive.cvreader.navigation

import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.ui.home.HomeFragment

fun NavRequest.toFragmentCreator(): () -> BaseFragment {
    return when (this) {
        NavRequest.Main.Home -> HomeFragment.creator()
    }
}
