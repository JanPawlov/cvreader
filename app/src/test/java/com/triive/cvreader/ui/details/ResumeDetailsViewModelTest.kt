package com.triive.cvreader.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.triive.cvreader.model.Resume
import com.triive.cvreader.navigation.NavDispatcher
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
class ResumeDetailsViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var navDispatcher: NavDispatcher

    private var coroutineDispatcher = Dispatchers.Unconfined

    private val resume = mock<Resume>()

    lateinit var viewModel: ResumeDetailsViewModel

    @Before
    fun setUp() {
        viewModel = ResumeDetailsViewModel(coroutineDispatcher, navDispatcher)
    }

    @Test
    fun `navigates back`() = runBlocking {
        viewModel.navigateBack()
        verify(navDispatcher).navigateBack(viewModel)
        verifyNoMoreInteractions(navDispatcher)
    }

    @After
    fun tearDown() {
        coroutineDispatcher.cancelChildren()
    }
}
