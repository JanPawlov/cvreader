package com.triive.cvreader

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.triive.cvreader.koin.startKoin
import com.triive.cvreader.utils.extensions.CrashReportingTree
import timber.log.Timber

class CVApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        initTimber()
        AndroidThreeTen.init(this)
    }

    @Suppress("ConstantConditionIf")
    private fun initTimber() {
        val tree = if (BuildConfig.CRASH_REPORTING_ENABLED) CrashReportingTree() else Timber.DebugTree()
        Timber.plant(tree)
    }
}
