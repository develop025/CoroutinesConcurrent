package hub.util.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hub.util.coroutines.concurrent.StringExtentions.Companion.logd
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
        while (true) {
            Thread.currentThread().name.toString().logd()
            delay(time)
            channel.send(s)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
//    fun CoroutineScope.counterActor() = actor<CounterMsg> {
//        var counter = 0 // actor state
//        for (msg in channel) { // iterate over incoming messages
//            when (msg) {
//                is IncCounter -> counter++
//                is GetCounter -> msg.response.complete(counter)
//            }
//        }
//    }
//    suspend fun massiveRun(action: suspend () -> Unit) {
//        val n = 100  // number of coroutines to launch
//        val k = 1000 // times an action is repeated by each coroutine
//        val time = measureTimeMillis {
//            coroutineScope { // scope for coroutines
//                repeat(n) {
//                    launch {
//                        repeat(k) { action() }
//                    }
//                }
//            }
//        }
//        "Completed ${n * k} actions in $time ms".logd()
//    }
//
//    fun CoroutineScope.produceNumbers() = produce<Int> {
//        var x = 1
//        while (true) {
//            "CoroutineScope.produceNumbers $x".logd()
//            send(x++) // infinite stream of integers starting from 1
//        }
//    }
//
//    fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
//        for (x in numbers) {
//            "CoroutineScope.square send x*x x:$x".logd()
//            send(x * x)
//        }
//    }
//
//    val counterContext = newSingleThreadContext("CounterContext")
//    var counter = 0
//
//    fun main() = runBlocking {
//        withContext(Dispatchers.Default) {
//            massiveRun {
//                // confine each increment to a single-threaded context
//                withContext(counterContext) {
////                    Thread.currentThread().name.logd()
//                    counter++
//                }
//            }
//        }
//        "Counter = $counter".logd()
//    }
//
//    fun main1() = runBlocking {
//        // confine everything to a single-threaded context
//        withContext(counterContext) {
//            massiveRun {
//                Thread.currentThread().name.logd()
//                counter++
//            }
//        }
//        println("Counter = $counter")
//    }
//        runBlocking {
//            val reaas = CoroutineScope(Dispatchers.IO).launch {
//            val channel = Channel<Int>()
//            launch {
//                for (x in 1..5) channel.send(x * x)
//            }
//            repeat(5) { channel.receive().toString().logd() }
//            "Done!".logd()

//            val channel = Channel<String>()
//            launch {
//                Thread.currentThread().name.logd()
//                sendString(channel, "foo", 200L)
//            }
//            launch {
//                Thread.currentThread().name.logd()
//                sendString(channel, "BAR!", 500L)
//            }
//            repeat(6) { // receive first six
//                channel.receive().toString().logd()
//            }
//            coroutineContext.cancelChildren() // cancel all children to let main finish

//            val numbers = produceNumbers() // produces integers from 1 and on
//            val squares = square(numbers) // squares integers
//            repeat(10) {
//                "repeat".logd()
//                squares.receive().toString().logd() // print first five
//            }
//            "Done!".logd()
//            //coroutineContext.cancelChildren() // cancel children coroutines
//            squares.receive().toString().logd()
//            squares.receive().toString().logd()
//            squares.receive().toString().logd()


//                val channel = Channel<String>()
//                var x = 0
//                launch {
//                    while (true) {
//                    "add A $x".logd()
//                        "add $x".logd()
//                        channel.send(x++.toString())
//                        "send".logd()
//    }
//                "add end A".logd()
//                }

//            launch {
//                while (true) {
//                    "add B $x".logd()
//                    channel.send(x++.toString())
//                    "send B $x".logd()
//                }
//                "add end B".logd()
//            }
//            channel.receive().logd()
//                launch {
//                    while (true) {
////                    delay(200L)
////                        "wait".logd()
//                        channel.receive().logd()
////                        "done".logd()
//                    }
//                }
//            }
//            delay(2000)
//            reaas.cancel()
//        }
//        val latch = CountDownLatch(4)
//        val first =
//            Worker(1000, latch, "WORKER-1")
//        val second =
//            Worker(2000, latch, "WORKER-2")
//        val third =
//            Worker(3000, latch, "WORKER-3")
//        val fourth =
//            Worker(4000, latch, "WORKER-4")
//        val fifth =
//            Worker(5000, latch, "WORKER-5")
//        first.start()
//        second.start()
//        third.start()
//
//
//        // The main task waits for four threads
//        // The main task waits for four threads
////        thread {
////            Thread.sleep(4000)
//            println("before await")
//            latch.await()
//            println("after await")
////        }
//        fourth.start()
//        fifth.start()
//CountDownLatch(2).
//}
//    internal class Worker(
//        private val delay: Int, private val latch: CountDownLatch,
//        name: String?
//    ) : Thread(name) {
//        override fun run() {
//            try {
//                sleep(delay.toLong())
//                latch.countDown()
//                println(currentThread().name + " finished")
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//        }
//
//    }
//}