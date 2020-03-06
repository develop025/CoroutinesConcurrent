package hub.util.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.CountDownLatch
import kotlin.util.coroutines.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val latch = CountDownLatch(4)

        val first =
            Worker(1000, latch, "WORKER-1")
        val second =
            Worker(2000, latch, "WORKER-2")
        val third =
            Worker(3000, latch, "WORKER-3")
        val fourth =
            Worker(4000, latch, "WORKER-4")
        first.start()
        second.start()
        third.start()
        fourth.start()

        // The main task waits for four threads
        // The main task waits for four threads
        latch.await()
//CountDownLatch(2).

    }

    internal class Worker(
        private val delay: Int, private val latch: CountDownLatch,
        name: String?
    ) : Thread(name) {
        override fun run() {
            try {
                sleep(delay.toLong())
                latch.countDown()
                println(currentThread().name + " finished")
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

    }
}
