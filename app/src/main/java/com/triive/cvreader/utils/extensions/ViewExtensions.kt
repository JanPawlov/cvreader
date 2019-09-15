package com.triive.cvreader.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gturedi.views.CustomStateOptions
import com.gturedi.views.StatefulLayout
import com.triive.cvreader.R

fun ViewGroup.inflate(@LayoutRes layoutResId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutResId, this, attachToRoot)

fun ImageView.loadWithGlide(url: String?) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun RecyclerView.addVerticalDividerDecoration() {
    if (itemDecorationCount == 0) {
        addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}

fun StatefulLayout.showFetchError(onButtonClick: () -> Unit) {
    showCustom(
        CustomStateOptions()
            .image(R.drawable.ic_warning)
            .message(context.getString(R.string.fetch_error))
            .buttonClickListener { onButtonClick() }
            .buttonText(context.getString(R.string.retry)))
}
