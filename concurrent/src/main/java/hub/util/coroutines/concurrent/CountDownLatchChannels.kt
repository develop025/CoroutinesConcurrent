package hub.util.coroutines.concurrent

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.sync.Mutex

open class CountDownLatchChannels<T>(
    private val filter: Any?,
    private val dispatcher: CoroutineDispatcher
) {
    private val awaitLocker = Any()

    private val awaitLocker = Any()
    private val awaitMutex = Mutex()
    private val channel = Channel<T>()

    suspend fun add() {
        awaitMutex.lock(awaitLocker)
    }

    suspend fun countDown(result: T) {
        channel.send(result)
    }

    suspend fun await() {
        while (true) {
            val resurlt = channel.receive()
        }
    }
}