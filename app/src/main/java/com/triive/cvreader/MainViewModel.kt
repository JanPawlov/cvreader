package com.triive.cvreader

import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.navigation.NavRequest
import com.triive.cvreader.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher) {
    val navigationChanges = navDispatcher.navigationChanges

    fun openStartScreen() = launch {
        navDispatcher.navigate(NavRequest.Main.Home)
    }
}
