package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MenuActivity : AppCompatActivity() {

    private var currentOrderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order)

        updateOrderSummary()

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
            updateOrderSummary()
        }

        // 🔴 Confirm Order Button
        findViewById<Button>(R.id.btnConfirmOrder).setOnClickListener {
            if (currentOrderId == null) {
                currentOrderId = System.currentTimeMillis().toString()
            }

            val orderList = OrderManager.getOrders()
            if (orderList.isEmpty()) {
                Toast.makeText(this, "No orders to confirm.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            for (order in orderList) {
                sendOrderToServer(
                    currentOrderId!!,
                    order.itemName,
                    order.quantity.toString(),
                    order.price.toString()
                )
            }

            OrderManager.clearOrders()
            updateOrderSummary()
            Toast.makeText(this, "Order confirmed and sent to server!", Toast.LENGTH_LONG).show()

            currentOrderId = null
        }
    }

    // 🧾 Dynamically show each item with a Remove button
    private fun updateOrderSummary() {
        val orderList = OrderManager.getOrders()
        val container = findViewById<LinearLayout>(R.id.orderListContainer)
        container.removeAllViews()

        if (orderList.isEmpty()) {
            val emptyText = TextView(this)
            emptyText.text = "No orders yet."
            emptyText.textSize = 18f
            container.addView(emptyText)
            return
        }

        for (order in orderList) {
            val itemLayout = LinearLayout(this)
            itemLayout.orientation = LinearLayout.HORIZONTAL
            itemLayout.setPadding(0, 10, 0, 10)

            val itemText = TextView(this)
            itemText.text = "${order.quantity} x ${order.itemName} - ₱${order.price * order.quantity}"
            itemText.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)

            val deleteBtn = Button(this)
            deleteBtn.text = "Remove"
            deleteBtn.setOnClickListener {
                OrderManager.removeItem(order.itemName)
                updateOrderSummary()
            }

            itemLayout.addView(itemText)
            itemLayout.addView(deleteBtn)
            container.addView(itemLayout)
        }
    }

    private fun sendOrderToServer(orderId: String, itemName: String, quantity: String, price: String) {
        Thread {
            try {
                val encodedItemName = URLEncoder.encode(itemName, "UTF-8")
                val urlString = "http://192.168.1.3/MP/add_orders.php?order_id=$orderId&item_name=$encodedItemName&quantity=$quantity&price=$price"

                val url = URL(urlString)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

                val responseCode = conn.responseCode
                val response = conn.inputStream.bufferedReader().readText()

                Log.d("ServerResponse", "Response Code: $responseCode\n$response")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ServerError", "Error sending order: ${e.message}")
            }
        }.start()
    }
}
