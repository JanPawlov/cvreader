package com.triive.cvreader.ui.details

import androidx.core.os.bundleOf
import com.triive.cvreader.R
import com.triive.cvreader.model.Resume
import com.triive.cvreader.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

import org.koin.core.parameter.parametersOf

class ResumeDetailsFragment : BaseFragment() {

    override val layoutRes = R.layout.resume_details_fragment
    override val viewModel: ResumeDetailsViewModel by viewModel { parametersOf(arguments?.getParcelable(RESUME_KEY)) }
    override fun onBackPressed() = viewModel.navigateBack()

    companion object {
        private const val RESUME_KEY = "resume"
        fun creator(resume: Resume): () -> ResumeDetailsFragment = {
            ResumeDetailsFragment().apply { arguments = bundleOf(RESUME_KEY to resume) }
        }
    }
}
