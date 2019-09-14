package com.triive.cvreader.ui.browser.recyclerview

import android.view.View
import android.view.ViewGroup
import com.triive.cvreader.model.Resume
import com.triive.cvreader.utils.recyclerview.BaseAdapter

class ResumeAdapter(onResumeClick: (resume: Resume, transactionViews: List<View>) -> Unit) : BaseAdapter<Resume, ResumeViewHolder>(onClick = onResumeClick) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeViewHolder = ResumeViewHolder(parent)
}
