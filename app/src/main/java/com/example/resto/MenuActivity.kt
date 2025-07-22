package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order)

        // Load and show order list immediately
        val txtOrderSummary = findViewById<TextView>(R.id.txtOrderSummary)
        val orders = OrderManager.getOrders().joinToString("\n")
        txtOrderSummary.text = if (orders.isNotEmpty()) orders else "No orders yet."

        findViewById<Button>(R.id.btnChicken).setOnClickListener {
            startActivity(Intent(this, Chicken::class.java))
        }

        findViewById<Button>(R.id.btnSides).setOnClickListener {
            startActivity(Intent(this, Sides::class.java))
        }

        findViewById<Button>(R.id.btnPasta).setOnClickListener {
            startActivity(Intent(this, Pasta::class.java))
        }

        findViewById<Button>(R.id.btnDrinks).setOnClickListener {
            startActivity(Intent(this, Drinks::class.java))
        }

        findViewById<Button>(R.id.btnShowOrder).setOnClickListener {
            // When clicked, show current orders in the txtOrderSummary TextView
            val orders = OrderManager.getOrders().joinToString("\n")
            val txtOrderSummary = findViewById<TextView>(R.id.txtOrderSummary)
            txtOrderSummary.text = if (orders.isNotEmpty()) orders else "No orders yet."
        }

        findViewById<Button>(R.id.btnConfirmOrder).setOnClickListener {
            val orders = OrderManager.getOrders()

            if (orders.isEmpty()) {
                Toast.makeText(this, "No orders to confirm.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //DITO ILALAGAY YUNG CODE PARA IPASOK SA DATABASE
            // Example: send to database logic (replace with your actual database call)
            //sendOrderToDatabase(orders)

            // Clear orders after confirming
            OrderManager.clearOrders()
            findViewById<TextView>(R.id.txtOrderSummary).text = "No orders yet."

            Toast.makeText(this, "Order confirmed!", Toast.LENGTH_LONG).show()
        }

    }
}
