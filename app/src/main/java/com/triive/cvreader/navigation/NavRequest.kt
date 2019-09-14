package com.triive.cvreader.navigation

import com.triive.cvreader.R
import com.triive.cvreader.model.Resume

sealed class NavRequest {
    abstract val tag: String
    abstract val containerId: Int

    open var navigationMode: NavigationMode = NavigationMode.ADD

    sealed class Main(override val tag: String) : NavRequest() {
        override val containerId = R.id.container

        object Home : Main("home")
        data class ResumeDetails(val resume: Resume): Main(resume.photo)
    }
}
