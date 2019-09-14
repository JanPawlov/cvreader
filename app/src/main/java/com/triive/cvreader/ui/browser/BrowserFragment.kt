package com.triive.cvreader.ui.browser

import android.os.Bundle
import android.view.View
import androidx.transition.TransitionSet
import com.triive.cvreader.R
import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.ui.browser.recyclerview.ResumeAdapter
import com.triive.cvreader.utils.extensions.addVerticalDividerDecoration
import com.triive.cvreader.utils.extensions.default
import com.triive.cvreader.utils.extensions.observe
import kotlinx.android.synthetic.main.browser_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrowserFragment : BaseFragment() {

    override val layoutRes = R.layout.browser_fragment
    override val viewModel: BrowserViewModel by viewModel()

    init {
        sharedElementEnterTransition = TransitionSet().default()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeResumesList()
        viewModel.fetchResumes()
    }

    private fun observeResumesList() {
        viewModel.resumes.observe(viewLifecycleOwner, (resumeBrowserRecyclerView.adapter as ResumeAdapter)::submitList)
    }

    private fun setRecyclerView() {
        resumeBrowserRecyclerView.apply {
            adapter = ResumeAdapter { resume, transactionViews -> viewModel.onResumeClick(resume, transactionViews) }
            addVerticalDividerDecoration()
        }
    }

    companion object {
        fun creator() = ::BrowserFragment
    }
}
