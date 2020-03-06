package kotlin.util.coroutines.concurrent

import java.util.concurrent.CountDownLatch

class CountDownLatch(count:Int) {

    init {
        require(count >= 0) { "count < 0" }
//        this.sync = CountDownLatch.Sync(count)
    }
    suspend fun countDown(){

    }
//    class Sync constructor(count: Int) : AbstractQueuedSynchronizer() {
//        var count: Int = count
//            private set
//            get() = state
//
//
//        override fun tryAcquireShared(acquires: Int): Int {
//            return if (state == 0) 1 else -1
//        }
//
//        override fun tryReleaseShared(releases: Int): Boolean {
//            while (true) {
//                val c = state
//                if (c == 0) return false
//                val nextc = c - 1
//                if (compareAndSetState(c, nextc)) return nextc == 0
//            }
//        }
//
//        companion object {
//            private const val serialVersionUID = 4982264981922014374L
//        }
//    }
//
//    private val sync: Sync? = null

}