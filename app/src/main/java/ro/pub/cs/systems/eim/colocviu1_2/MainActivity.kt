package ro.pub.cs.systems.eim.colocviu1_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var addButton: Button
    private lateinit var computeBtn: Button

    private var series = ""

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

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            Toast.makeText(this, "Suma este ${resultCode}", Toast.LENGTH_LONG).show()
        }

    }
}