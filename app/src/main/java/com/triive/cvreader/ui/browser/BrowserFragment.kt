package com.triive.cvreader.ui.browser

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.transition.Fade
import androidx.transition.TransitionSet
import com.triive.cvreader.R
import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.ui.browser.recyclerview.ResumeAdapter
import com.triive.cvreader.utils.extensions.addVerticalDividerDecoration
import com.triive.cvreader.utils.extensions.default
import com.triive.cvreader.utils.extensions.observe
import com.triive.cvreader.utils.extensions.showFetchError
import kotlinx.android.synthetic.main.browser_content.*
import kotlinx.android.synthetic.main.browser_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrowserFragment : BaseFragment() {

    override val layoutRes = R.layout.browser_fragment
    override val viewModel: BrowserViewModel by viewModel()

    init {
        sharedElementEnterTransition = TransitionSet().default()
        enterTransition = Fade()
        exitTransition = Fade()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeLiveData()
        viewModel.fetchResumes()
    }

    private fun observeLiveData() {
        viewModel.resumes.observe(viewLifecycleOwner, (resumeBrowserRecyclerView.adapter as ResumeAdapter)::submitList)
        viewModel.fetchResult.observe(viewLifecycleOwner, ::setViewState)
    }

    private fun setViewState(success: Boolean) {
        if (success) {
            browserStatefulLayout.showContent()
            showLoading(false)
        } else {
            browserStatefulLayout.showFetchError {
                browserStatefulLayout.showContent()
                showLoading(true)
                viewModel.fetchResumes()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        contentLoadingProgressBar.isVisible = isLoading
        resumeBrowserRecyclerView.isVisible = !isLoading
    }

    private fun setRecyclerView() {
        resumeBrowserRecyclerView.apply {
            adapter = ResumeAdapter { resume, transactionViews -> viewModel.onResumeClick(resume, transactionViews) }
            addVerticalDividerDecoration()
        }
    }

    companion object {
        fun creator(): () -> BrowserFragment = ::BrowserFragment
    }
}
