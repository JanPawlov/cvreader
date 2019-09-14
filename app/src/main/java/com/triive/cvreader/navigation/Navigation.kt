package com.triive.cvreader.navigation

import com.triive.cvreader.MainActivity

data class Navigation(private val action: NavigationAction) {
    private var consumed = false

    fun executeIfNotConsumed(mainActivity: MainActivity) {
        if (!consumed) {
            action(mainActivity)
            consumed = true
        }
    }
}
typealias NavigationAction = (MainActivity) -> Unit
