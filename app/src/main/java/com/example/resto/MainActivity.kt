package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.resto.RecetOrder.RecentOrderActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOrderNow = findViewById<Button>(R.id.btnOrderNow)
        btnOrderNow.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val btnRecentOrders = findViewById<Button>(R.id.btnRecentOrders)
        btnRecentOrders.setOnClickListener {
            val intent = Intent(this, RecentOrderActivity::class.java)
            startActivity(intent)
        }

        // TODO: Add functionality for recent orders
        }
    }
