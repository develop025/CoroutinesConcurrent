package hub.util.coroutines.concurrent

import android.util.Log

class StringExtentions {
    companion object {
        fun String.logD() {
            Log.d("CoroutinesConcurrent", this)
        }
    }
}