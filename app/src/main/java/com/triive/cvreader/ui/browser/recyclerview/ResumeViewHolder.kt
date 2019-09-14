package com.triive.cvreader.ui.browser.recyclerview

import android.view.View
import android.view.ViewGroup
import com.triive.cvreader.R
import com.triive.cvreader.model.Resume
import com.triive.cvreader.utils.extensions.inflate
import com.triive.cvreader.utils.extensions.loadWithGlide
import com.triive.cvreader.utils.recyclerview.BaseViewHolder
import kotlinx.android.synthetic.main.item_list_resume.*

class ResumeViewHolder private constructor(override val containerView: View) : BaseViewHolder<Resume>(containerView) {

    constructor(parent: ViewGroup) : this(parent.inflate(R.layout.item_list_resume))

    override fun bind(item: Resume, onClick: ((Resume) -> Unit)?) {
        itemResumeFullName.text = "${item.firstName} ${item.lastName}"
        itemResumePhoto.loadWithGlide(item.photo)
    }
}
