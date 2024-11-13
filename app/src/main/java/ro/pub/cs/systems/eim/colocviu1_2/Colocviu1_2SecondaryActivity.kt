package ro.pub.cs.systems.eim.colocviu1_2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Colocviu1_2SecondaryActivity : AppCompatActivity() {

    private var series = ""
    private var sum = 0
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colocviu12_secondary2)

        series = intent.getStringExtra("series")!!
        sum = series.split("+").map { it.toInt() }.sum()

        backButton = findViewById(R.id.button3)
        backButton.setOnClickListener {
            setResult(sum)
            Toast.makeText(this, "Suma este ${sum}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}