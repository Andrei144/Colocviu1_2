package ro.pub.cs.systems.eim.colocviu1_2

import android.app.Service
import android.content.Intent
import android.os.IBinder

class Colocviu1_2Service : Service() {

    private var processingThread: ProcessingThread? = null

    override fun onStartCommand(intent: Intent, sum: Int, startId: Int): Int {
        val sum = intent.getIntExtra("sum", 0)
        processingThread = ProcessingThread(this, sum)
        processingThread!!.start()
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        processingThread?.stopThread()
    }
}