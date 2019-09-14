package com.triive.cvreader.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.transition.Fade
import androidx.transition.TransitionSet
import com.triive.cvreader.R
import com.triive.cvreader.model.Resume
import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.utils.extensions.default
import com.triive.cvreader.utils.extensions.loadWithGlide
import kotlinx.android.synthetic.main.resume_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

import org.koin.core.parameter.parametersOf

class ResumeDetailsFragment : BaseFragment() {

    override val layoutRes = R.layout.resume_details_fragment
    override val viewModel: ResumeDetailsViewModel by viewModel()
    override fun onBackPressed() = viewModel.navigateBack(trainsitionViews)

    private val trainsitionViews: List<View> by lazy { listOf(detailsImageView, detailsFullName, detailsPosition) }

    init {
        sharedElementEnterTransition = TransitionSet().default()
        exitTransition = Fade()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showResumeData()
    }

    private fun showResumeData() {
        (arguments?.getParcelable(RESUME_KEY) as Resume?)?.let { resume ->
            detailsImageView.loadWithGlide(resume.photo)
            detailsFullName.text = "${resume.firstName} ${resume.lastName}"
            detailsPosition.text = resume.position
            detailsFullName.transitionName = "${resume.hashCode()}${resume.firstName}${resume.lastName}"
            detailsImageView.transitionName = "${resume.hashCode()}${resume.photo}"
            detailsPosition.transitionName = "${resume.hashCode()}${resume.position}"
        }
    }

    companion object {
        private const val RESUME_KEY = "resume"
        fun creator(resume: Resume): () -> ResumeDetailsFragment = {
            ResumeDetailsFragment().apply { arguments = bundleOf(RESUME_KEY to resume) }
        }
    }
}
