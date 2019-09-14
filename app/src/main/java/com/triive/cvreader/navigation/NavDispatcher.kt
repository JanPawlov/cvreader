package com.triive.cvreader.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.triive.cvreader.MainActivity
import com.triive.cvreader.extensions.removeOldFragments
import com.triive.cvreader.extensions.swap
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class NavDispatcher {
    private val _navigationChanges = MutableLiveData<Navigation>()
    private val mainBackStack = BackStack()
    private val nestedBackStacks = mutableMapOf<String, BackStack>()
    val navigationChanges: LiveData<Navigation> = _navigationChanges

    suspend fun navigate(navRequest: NavRequest, navigationMode: NavigationMode = NavigationMode.ADD) = withContext(Dispatchers.Main) {
        navigateMain(navRequest, navigationMode)
    }

    /**
     * @return true if navigate back will be performed
     */

    fun navigateBack(coroutineScope: CoroutineScope): Boolean {
        val backStack = mainBackStack.peek()?.let {
            nestedBackStacks[it.tag]
        }
        var isNavigationPerformed = false
        if (backStack != null && backStack.size > 1) {
            isNavigationPerformed = coroutineScope.navigateBackWith(backStack)
        }
        if (!isNavigationPerformed && mainBackStack.size > 1) {
            isNavigationPerformed = coroutineScope.navigateBackWith(mainBackStack)
        }
        return isNavigationPerformed
    }

    private fun CoroutineScope.navigateBackWith(backStack: BackStack): Boolean {
        backStack.peekPrevious()?.let { navRequest ->
            launch { navigate(navRequest, NavigationMode.REPLACE_TOP) }
            return true
        }
        return false
    }

    private suspend fun navigateMain(navRequest: NavRequest, navigationMode: NavigationMode) {
        postNavigation(mainBackStack, navRequest, navigationMode) { supportFragmentManager }
    }

    private suspend fun postNavigation(
        backStack: BackStack,
        navRequest: NavRequest,
        navigationMode: NavigationMode,
        fragmentManagerProvider: MainActivity.() -> FragmentManager?
    ) {
        suspendCancellableCoroutine<Unit> { continuation ->
            _navigationChanges.postValue(Navigation { mainActivity ->
                val fragmentTagsToRemove = backStack.push(navRequest, navigationMode).map { it.tag }
                fragmentManagerProvider(mainActivity)?.let { performFragmentTransaction(it, navRequest, fragmentTagsToRemove) }
                continuation.resume(Unit)
            })
        }
    }

    private fun performFragmentTransaction(fragmentManager: FragmentManager, navRequest: NavRequest, fragmentTagsToRemove: List<String>) {
        for (tag in fragmentTagsToRemove) {
            nestedBackStacks.remove(tag)
        }
        fragmentManager.commitNow {
            swap(navRequest.tag, navRequest.toFragmentCreator(), fragmentManager, navRequest.containerId)
            removeOldFragments(fragmentTagsToRemove, navRequest.tag, fragmentManager)
        }
    }
}
