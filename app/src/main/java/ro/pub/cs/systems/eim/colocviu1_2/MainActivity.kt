package ro.pub.cs.systems.eim.colocviu1_2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var addButton: Button
    private lateinit var computeBtn: Button

    private var series = ""

    private val intentFilter = IntentFilter()
    private val messageBroadcastReceiver = MessageBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextText)
        textView = findViewById(R.id.textView2)
        addButton = findViewById(R.id.button)
        computeBtn = findViewById(R.id.button2)

        addButton.setOnClickListener {
            val text = editText.text.toString()

            if(text.isNotEmpty()) {
                if (series.isEmpty())
                    series += text
                else
                    series += ("+" + text)
            }

            textView.text = series
        }

        computeBtn.setOnClickListener {

            val intent = Intent(
                this,
                Colocviu1_2SecondaryActivity::class.java
            )
            intent.putExtra("series", series)
            startActivityForResult(intent, 0)
        }

        if (savedInstanceState != null) {
            if(savedInstanceState.containsKey("firstNumber")){
                series = savedInstanceState.getString("series").toString()
                textView.text = series
            }
        }else{
            series = ""
        }

        intentFilter.addAction("ro.pub.cs.systems.eim.practicaltest01.over10")


    }

    // Save the state of the activity
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("series", series)
    }

    // Restore the saved state
    @SuppressLint("SetTextI18n")
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState.containsKey("series")){
            series = savedInstanceState.getString("series").toString()
            textView.text = series
        }
        Toast.makeText(this, series, Toast.LENGTH_LONG).show()
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        textView.text = series
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        registerReceiver(messageBroadcastReceiver, intentFilter, RECEIVER_EXPORTED)
    }

    // Unregister the broadcast receiver
    override fun onPause() {
        unregisterReceiver(messageBroadcastReceiver)
        super.onPause()
    }
}