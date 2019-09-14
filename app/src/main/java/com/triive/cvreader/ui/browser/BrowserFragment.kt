package com.triive.cvreader.ui.browser

import android.os.Bundle
import android.view.View
import com.triive.cvreader.R
import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.utils.extensions.addVerticalDividerDecoration
import kotlinx.android.synthetic.main.browser_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BrowserFragment : BaseFragment() {

    override val layoutRes= R.layout.browser_fragment
    override val viewModel: BrowserViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView(){
        resumeBrowserRecyclerView.apply {
            adapter
            addVerticalDividerDecoration()
        }
    }

    companion object{
        fun creator() = ::BrowserFragment
    }
}
