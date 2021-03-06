package hub.util.coroutines.concurrent

import hub.util.coroutines.concurrent.StringExtentions.Companion.logD

open class CountDownLatchFilter(count: Int, private val filter: Any?) : CountDownLatch(count) {
    init {
        require(countAtomic.get() >= 0) { "count < 0" }
    }

    suspend fun countDown(result: Any?) {
        super.countDown()
        "${this.javaClass.simpleName} result:$result".logD()
        if (filter != null && filter == result) {
            "${this.javaClass.simpleName} countDown unlock with filter result:$result".logD()
            general.unlock()
        }
    }
}