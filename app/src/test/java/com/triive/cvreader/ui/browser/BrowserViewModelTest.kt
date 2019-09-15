package com.triive.cvreader.ui.browser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.triive.cvreader.api.ApiRepository
import com.triive.cvreader.model.Resume
import com.triive.cvreader.navigation.NavDispatcher
import com.triive.cvreader.navigation.NavRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BrowserViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var navDispatcher: NavDispatcher
    @Mock
    lateinit var apiRepository: ApiRepository

    private var coroutineDispatcher = Dispatchers.Unconfined

    private val resume = mock<Resume>()
    private val photo = "Stub"

    lateinit var viewModel: BrowserViewModel

    @Before
    fun setUp() {
        viewModel = BrowserViewModel(coroutineDispatcher, navDispatcher, apiRepository)
        whenever(resume.photo).thenReturn(photo)
    }

    @Test
    fun `navigated to resume details`() = runBlocking {
        viewModel.onResumeClick(resume, null)
        verify(navDispatcher).navigate(NavRequest.Main.ResumeDetails(resume))
    }

    @After
    fun tearUp() {
        coroutineDispatcher.cancelChildren()
    }
}
