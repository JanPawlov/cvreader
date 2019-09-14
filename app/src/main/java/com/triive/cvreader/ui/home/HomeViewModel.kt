package com.triive.cvreader.ui.home

import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.ui.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class HomeViewModel(
    backgroundDispatcher: CoroutineDispatcher,
    private val navDispatcher: NavDispatcher
) : BaseViewModel(backgroundDispatcher) {

}
