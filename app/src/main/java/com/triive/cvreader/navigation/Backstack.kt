package com.triive.cvreader.navigation

class BackStack : Collection<NavRequest> {
    private var navRequests: MutableList<NavRequest> = mutableListOf()

    fun pop() = peek()?.also { navRequests.remove(it) }

    fun peek() = navRequests.lastOrNull()

    fun peekPrevious() = navRequests.dropLast(1).lastOrNull()

    fun push(element: NavRequest, navigationMode: NavigationMode = NavigationMode.ADD): List<NavRequest> {
        val removedRequests = if (element in navRequests) {
            when (navigationMode) {
                NavigationMode.ADD -> emptyList()
                NavigationMode.REPLACE_TOP -> navRequests.takeLastWhile { it != element }
                NavigationMode.REPLACE_ALL -> navRequests
            }
        } else {
            when (navigationMode) {
                NavigationMode.ADD -> emptyList()
                NavigationMode.REPLACE_TOP -> listOfNotNull(pop())
                NavigationMode.REPLACE_ALL -> navRequests
            }
        }
        navRequests.removeAll(removedRequests + element)
        navRequests.add(element)
        return removedRequests
    }

    override val size get() = navRequests.size

    override fun contains(element: NavRequest) = navRequests.contains(element)

    override fun containsAll(elements: Collection<NavRequest>) = navRequests.containsAll(elements)

    override fun isEmpty() = navRequests.isEmpty()

    override fun iterator() = navRequests.iterator()
}
