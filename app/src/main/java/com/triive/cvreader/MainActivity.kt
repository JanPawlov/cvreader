package com.triive.cvreader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.triive.cvreader.ui.BaseFragment
import com.triive.cvreader.utils.extensions.observe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main + SupervisorJob()
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        observeNavigationChanges()
        if (savedInstanceState == null) {
            viewModel.openStartScreen()
        }
    }

    private fun observeNavigationChanges() {
        viewModel.navigationChanges
            .observe(this) { it.executeIfNotConsumed(this) }
    }

    private fun getCurrentFragment() = supportFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    override fun onBackPressed() {
        if (getCurrentFragment()?.onBackPressed() != true) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        coroutineContext.cancel()
        super.onDestroy()
    }
}
