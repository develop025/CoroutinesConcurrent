package hub.util.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hub.util.coroutines.concurrent.CountDownLatchFilter
import hub.util.coroutines.concurrent.StringExtentions.Companion.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
//            val general = Mutex()
//            repeat(5) {
//                general.lock()
//                "$it before delay".logd()
//                delay(500)
//                "$it after delay".logd()
//                general.unlock()
//            }
            val countDown = CountDownLatchFilter(5, "TRUE")

            CoroutineScope(Dispatchers.IO).launch {
                countDown.await{
                "after unlock".logd()
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(100)
                countDown.countDown("asdasd")
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(200)
                countDown.countDown("asdasd")
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(300)
                countDown.countDown("TRUE")
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(400)
                countDown.countDown("asdasd")
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(500)
                countDown.countDown("asdasd")
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(600)
                countDown.countDown("asdasd")
            }

            CoroutineScope(Dispatchers.IO).launch {
                delay(700)
                countDown.countDown("asdasd")
            }
        }
    }
}