package com.triive.cvreader.navigation

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import com.triive.cvreader.MainActivity
import com.triive.cvreader.utils.SafeLiveData
import com.triive.cvreader.utils.extensions.removeOldFragments
import com.triive.cvreader.utils.extensions.swap
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class NavDispatcher : SafeLiveData.LiveDataContainer {
    val navigationChanges = SafeLiveData<Navigation>()
    private val mainBackStack = BackStack()
    private val nestedBackStacks = mutableMapOf<String, BackStack>()

    suspend fun navigate(
        navRequest: NavRequest,
        transactionViews: List<View>? = null,
        navigationMode: NavigationMode = NavigationMode.ADD
    ) = withContext(Dispatchers.Main) {
        navigateMain(navRequest, navigationMode, transactionViews)
    }

    /**
     * @return true if navigate back will be performed
     */

    fun navigateBack(coroutineScope: CoroutineScope, transactionViews: List<View>? = null): Boolean {
        val backStack = mainBackStack.peek()?.let {
            nestedBackStacks[it.tag]
        }
        var isNavigationPerformed = false
        if (backStack != null && backStack.size > 1) {
            isNavigationPerformed = coroutineScope.navigateBackWith(backStack, transactionViews)
        }
        if (!isNavigationPerformed && mainBackStack.size > 1) {
            isNavigationPerformed = coroutineScope.navigateBackWith(mainBackStack, transactionViews)
        }
        return isNavigationPerformed
    }

    private fun CoroutineScope.navigateBackWith(backStack: BackStack, transactionViews: List<View>? = null): Boolean {
        backStack.peekPrevious()?.let { navRequest ->
            launch { navigate(navRequest, transactionViews, NavigationMode.REPLACE_TOP) }
            return true
        }
        return false
    }

    private suspend fun navigateMain(navRequest: NavRequest, navigationMode: NavigationMode, transactionViews: List<View>? = null) {
        postNavigation(mainBackStack, navRequest, navigationMode, transactionViews) { supportFragmentManager }
    }

    private suspend fun postNavigation(
        backStack: BackStack,
        navRequest: NavRequest,
        navigationMode: NavigationMode,
        transactionViews: List<View>? = null,
        fragmentManagerProvider: MainActivity.() -> FragmentManager?
    ) {
        suspendCancellableCoroutine<Unit> { continuation ->
            navigationChanges.postValue(Navigation { mainActivity ->
                val fragmentTagsToRemove = backStack.push(navRequest, navigationMode).map { it.tag }
                fragmentManagerProvider(mainActivity)?.let { performFragmentTransaction(it, navRequest, fragmentTagsToRemove, transactionViews) }
                continuation.resume(Unit)
            })
        }
    }

    private fun performFragmentTransaction(
        fragmentManager: FragmentManager,
        navRequest: NavRequest,
        fragmentTagsToRemove: List<String>,
        transactionViews: List<View>? = null
    ) {
        for (tag in fragmentTagsToRemove) {
            nestedBackStacks.remove(tag)
        }
        fragmentManager.commitNow {
            swap(navRequest.tag, navRequest.toFragmentCreator(), fragmentManager, transactionViews, navRequest.containerId)
            removeOldFragments(fragmentTagsToRemove, navRequest.tag, fragmentManager)
        }
    }
}
