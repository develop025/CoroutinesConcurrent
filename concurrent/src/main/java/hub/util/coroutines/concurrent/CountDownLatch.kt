package hub.util.coroutines.concurrent

import hub.util.coroutines.concurrent.StringExtentions.Companion.logD
import kotlinx.coroutines.sync.Mutex
import java.util.concurrent.atomic.AtomicInteger

open class CountDownLatch(count: Int) {
    var countAtomic: AtomicInteger = AtomicInteger(count)
    protected val general = Mutex()

    init {
        require(countAtomic.get() >= 0) { "count < 0" }
    }

    suspend fun await(afterUnlock: () -> Unit) {
        general.lock()
        general.lock()
        afterUnlock.invoke()
    }

    suspend fun countDown() {
        if (countAtomic.get() <= 0) {
            "${this.javaClass.simpleName} countDown already finished".logD()
            return
        }

        countAtomic.decrementAndGet()
        if (countAtomic.get() <= 0) {
            "${this.javaClass.simpleName} unlock".logD()
            general.unlock()
        }

    }

    suspend fun getCount() = countAtomic.get()
}