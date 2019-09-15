package com.triive.cvreader.utils.extensions

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.ChangeBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import com.triive.cvreader.ui.BaseFragment
import timber.log.Timber

fun FragmentTransaction.swap(
    tag: String,
    createFragment: () -> BaseFragment,
    fragmentManager: FragmentManager,
    transactionViews: List<View>?,
    containerId: Int
) {
    with(fragmentManager) {
        val currentFragment = findFragmentById(containerId)
        val fragmentToAttach = findFragmentByTag(tag)
        if (currentFragment != fragmentToAttach) {
            currentFragment?.let(::detach)
        }
        transactionViews?.filter { it.transitionName != null }?.forEach { addSharedElement(it, it.transitionName) }
        fragmentToAttach?.let(::attach) ?: add(containerId, createFragment(), tag)

        Timber.i("Swap: %s, isFreshFragment: %b", tag, fragmentToAttach == null)
    }
}

fun FragmentTransaction.removeOldFragments(fragmentsToRemove: List<String>, tag: String, fragmentManager: FragmentManager) {
    fragmentsToRemove
        .filter { it != tag }
        .mapNotNull(fragmentManager::findFragmentByTag)
        .forEach { remove(it) }
    Timber.i("Removed fragments: %s", fragmentsToRemove)
}

fun TransitionSet.default(): TransitionSet {
    setOrdering(TransitionSet.ORDERING_TOGETHER)
        .addTransition(ChangeBounds())
        .addTransition(ChangeTransform())
    return this
}
