package com.triive.cvreader

import android.app.Application
import com.triive.cvreader.utils.extensions.CrashReportingTree
import com.triive.cvreader.koin.startKoin
import timber.log.Timber

class CVApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        initTimber()
    }

    @Suppress("ConstantConditionIf")
    private fun initTimber() {
        val tree = if (BuildConfig.CRASH_REPORTING_ENABLED) CrashReportingTree() else Timber.DebugTree()
        Timber.plant(tree)
    }
}
