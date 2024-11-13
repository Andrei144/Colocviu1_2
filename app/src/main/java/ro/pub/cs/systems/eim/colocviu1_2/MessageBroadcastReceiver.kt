package ro.pub.cs.systems.eim.colocviu1_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MessageBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("MessageBroadcastReceiver", "onReceive() method has been invoked")
        Toast.makeText(p0, p1?.getStringExtra("BROADCAST_RECEIVER_EXTRA") ?: "no data", Toast.LENGTH_LONG).show()
    }
}