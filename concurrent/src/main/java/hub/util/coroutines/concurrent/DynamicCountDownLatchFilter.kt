package hub.util.coroutines.concurrent

import hub.util.coroutines.concurrent.StringExtentions.Companion.logd
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

//in progress
class DynamicCountDownLatchFilter(
    private val filter: Any?,
    private val dispatcher: CoroutineDispatcher
) {
    private val general = Mutex()
    private val generalLock = Any()
    private val resultMutex = Mutex()

    @Volatile
    private var result: Any? = null

    private val jobs = mutableListOf<Job>()
    private val suspendListMutex = mutableListOf<Mutex>()

//    override fun countDown(result: Any?) {
//        super.countDown()
//        count.decrementAndGet()
//        if (count.get() <= 0 || (filter != null && filter == result))
//            general.unlock(generalLock)
//    }

    fun start(suspendFunction: () -> Unit) {
        "start".logd()
        val job = CoroutineScope(dispatcher).launch {
            "launch".logd()
            val result = suspendFunction.invoke()
            if (result == filter) {
                "result == filter".logd()
                dispatcher.cancel()
            }
        }
        "jobs.add".logd()
        jobs.add(job)
    }

    suspend fun setResult(r: Any) {
        resultMutex.withLock {
            result = r
        }
    }

    suspend fun await() {
        if (suspendListMutex.count() > 0)
            general.lock(generalLock)
        else
            throw IllegalAccessException()
    }
}   
