package com.triive.cvreader.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.transition.Fade
import androidx.transition.TransitionSet
import com.triive.cvreader.R
import com.triive.cvreader.api.response.adapter.LocalDateTimeAdapter
import com.triive.cvreader.model.Resume
import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.utils.extensions.default
import com.triive.cvreader.utils.extensions.formatDate
import com.triive.cvreader.utils.extensions.loadWithGlide
import kotlinx.android.synthetic.main.item_resume_content.view.*
import kotlinx.android.synthetic.main.resume_details_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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
            showRemainingResumeContent(resume)
        }
    }

    private fun showRemainingResumeContent(resume: Resume) {
        showProfile(resume)
        showSkills(resume)
        showEmployement(resume)
        showLanguages(resume)
        showHobbies(resume)
    }

    private fun showProfile(resume: Resume) = with(resume) {
        addContentView(header = getString(R.string.profile), content = profile)
    }

    private fun showSkills(resume: Resume) = with(resume) {
        var skillsContent = ""
        skills.forEach { skill ->
            skillsContent += "$skill\n"
        }
        addContentView(header = getString(R.string.skills), content = skillsContent.trimEnd())
    }

    private fun showHobbies(resume: Resume) = with(resume) {
        addContentView(header = getString(R.string.hobbies), content = hobbies)
    }

    private fun showLanguages(resume: Resume) = with(resume) {
        addContentView(header = getString(R.string.languages), content = languages)
    }

    private fun showEmployement(resume: Resume) = with(resume) {
        val dateAdapter = LocalDateTimeAdapter()
        employement.forEach { employement ->
            val fromDate = dateAdapter.fromJson(employement.from)
            val toDate = dateAdapter.fromJson(employement.to)
            val timeInterval = fromDate.formatDate() + " - " + toDate.formatDate()
            var responsibilities = getString(R.string.responsibilities)
            employement.responsibilities.forEach { responsibility ->
                responsibilities += "\n$responsibility"
            }
            val content = "${employement.employer}\n" +
                    "\n${employement.position}\n" +
                    "\n${timeInterval}\n" +
                    responsibilities
            addContentView(header = getString(R.string.employment), content = content)
        }
    }

    private fun addContentView(header: String, content: String) {
        LayoutInflater.from(context).inflate(R.layout.item_resume_content, additionalContentContainer, false).also {
            it.itemHeader.text = header
            it.itemContent.text = content
            additionalContentContainer.addView(it)
        }
    }

    companion object {
        private const val RESUME_KEY = "resume"
        fun creator(resume: Resume): () -> ResumeDetailsFragment = {
            ResumeDetailsFragment().apply { arguments = bundleOf(RESUME_KEY to resume) }
        }
    }
}
