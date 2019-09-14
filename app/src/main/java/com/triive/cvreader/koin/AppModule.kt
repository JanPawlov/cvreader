package com.triive.cvreader.koin

import com.triive.cvreader.CVApplication
import com.triive.cvreader.MainViewModel
import com.triive.cvreader.navigation.NavDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.experimental.builder.single

fun CVApplication.startKoin() {
    startKoin {
        androidLogger(Level.DEBUG)
        printLogger(Level.DEBUG)
        androidContext(this@startKoin)
        modules(appModule)
    }
}

private val appModule = module {
    single { Dispatchers.IO }
    single<NavDispatcher>()
    viewModel<MainViewModel>()
}
