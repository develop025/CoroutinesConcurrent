package hub.util.coroutines.concurrent

import kotlinx.coroutines.sync.Mutex
import java.util.concurrent.atomic.AtomicInteger

class CountDownLatchFilter(override val count: AtomicInteger, private val filter: Any?) :
    CountDownLatch(count) {
    private val general = Mutex()
    private val generalLock = Any()

    init {
        require(count.get() >= 0) { "count < 0" }
    }

    override fun countDown(result: Any?) {
        super.countDown()
        count.decrementAndGet()
        if (count.get() <= 0 || (filter != null && filter == result))
            general.unlock(generalLock)
    }
}