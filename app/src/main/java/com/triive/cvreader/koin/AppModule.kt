package com.triive.cvreader.koin

import com.triive.cvreader.CVApplication
import com.triive.cvreader.MainViewModel
import com.triive.cvreader.api.ApiFactory
import com.triive.cvreader.api.ApiRepository
import com.triive.cvreader.api.RetrofitInterceptor
import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.ui.browser.BrowserViewModel
import com.triive.cvreader.ui.details.ResumeDetailsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
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
    single<RetrofitInterceptor>()
    single<ApiFactory>()
    single { ApiRepository(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { BrowserViewModel(get(), get(),get()) }
    viewModel { ResumeDetailsViewModel(get(), get()) }
}
