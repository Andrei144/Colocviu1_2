package ro.pub.cs.systems.eim.colocviu1_2

import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Date

class ProcessingThread(context: Context, sum: Int) : Thread() {

    private var context: Context? = context
    private var isRunning = true


    private var sum = 0

    init {
        this.context = context

        this.sum = sum
    }

    override fun run() {
        while (isRunning) {
            if(sum > 10)
                sendMessage()
            sleep()
        }
        Log.d("PROCESSING_THREAD_TAG", "Thread has stopped!")
    }

    private fun sendMessage() {
        val intent = Intent().setAction("ro.pub.cs.systems.eim.practicaltest01.over10")
        intent.putExtra(
            "BROADCAST_RECEIVER_EXTRA",
            Date(System.currentTimeMillis()).toString() + " " + sum
        )
        context!!.sendBroadcast(intent)
    }

    private fun sleep() {
        try {
            sleep(2000)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }

}