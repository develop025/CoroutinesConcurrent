package hub.util.coroutines.concurrent

class CountDownLatch(count: Int) {
    init {
        require(count >= 0) { "count < 0" }
//        this.sync = CountDownLatch.Sync(count)
    }


    fun await() {
        Lock
    //    sync.acquireSharedInterruptibly(1)
    }

    fun countDown() {
        sync.releaseShared(1)
    }

    fun getCount(): Long {
        return sync.getCount()
    }
}