package hub.util.coroutines.concurrent

import kotlinx.coroutines.sync.Mutex
import java.util.concurrent.atomic.AtomicInteger

open class CountDownLatch(open val count: AtomicInteger) {
    private val general = Mutex()
    private val generalLock = Any()

    init {
        require(count.get() >= 0) { "count < 0" }
    }

    suspend fun await() {
        general.lock(generalLock)
    }

    fun countDown() {
        count.decrementAndGet()
        if (count.get() <= 0)
            general.unlock(generalLock)
    }

    fun getCount() = count.get()
    open fun countDown(result: Any?) {}
}