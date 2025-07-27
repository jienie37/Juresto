package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.resto.RecetOrder.RecentOrderActivity
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check server connection on startup
        checkServerConnection()

        val btnOrderNow = findViewById<Button>(R.id.btnOrderNow)
        btnOrderNow.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            checkServerConnection()
        }

        val btnRecentOrders = findViewById<Button>(R.id.btnRecentOrders)
        btnRecentOrders.setOnClickListener {
            val intent = Intent(this, RecentOrderActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to test connection to PHP server
    private fun checkServerConnection() {
        Thread {
            try {
                val url = URL("http://192.168.1.3/MP/connects.php")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Failed to connect to server", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }
}
