package com.example.resto.RecetOrder
import  OrderItem
import OrdersSummary
import ProcessedOrder
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.resto.R
import android.widget.*
import androidx.annotation.OptIn
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import kotlinx.coroutines.*
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Locale

class RecentOrderActivity : AppCompatActivity() {

    private lateinit var ordersListView: ListView
    private lateinit var totalTextView: TextView
    private val baseUrl = "http://192.168.1.12/MP/get_orders.php" // ← adjust IP and folder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_order)

        ordersListView = findViewById(R.id.ordersListView)
        totalTextView = findViewById(R.id.totalTextView)

        val tabGroup = findViewById<RadioGroup>(R.id.tabGroup)
        val closeButton = findViewById<Button>(R.id.closeButton)

        tabGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.tabDaily -> fetchOrders("daily")
                R.id.tabWeekly -> fetchOrders("weekly")
                R.id.tabMonthly -> fetchOrders("monthly")
            }
        }

        closeButton.setOnClickListener { finish() }

        fetchOrders("daily") // default
    }

    @OptIn(UnstableApi::class)
    private fun fetchOrders(range: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("http://192.168.1.12/MP/get_orders.php?period=$range")
                Log.d("FETCH_ORDERS", "Request URL: $url")

                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "GET"

                val data = conn.inputStream.bufferedReader().readText()
                Log.d("FETCH_ORDERS", "Raw response: $data")

                val jsonArray = JSONArray(data)

                val groupedOrders = mutableMapOf<String, MutableList<OrderItem>>()
                val timestamps = mutableMapOf<String, Long>()

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val orderId = obj.getString("order_id")
                    val item = OrderItem(
                        "item",
                        obj.getString("item_name"),
                        obj.getInt("quantity"),
                        obj.getDouble("price")
                    )

                    val timestampStr = obj.getString("date_order") // the column from MySQL
                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val date = try {
                        sdf.parse(timestampStr)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                    val timestamp = date?.time ?: 0L

                    groupedOrders.getOrPut(orderId) { mutableListOf() }.add(item)
                    timestamps[orderId] = timestamp
                }

                val orders = groupedOrders.map { (id, items) ->
                    ProcessedOrder(id, items, timestamps[id] ?: 0L)
                }

                val total = orders.sumOf { it.orderTotal }
                val summary = OrdersSummary(orders, total)

                withContext(Dispatchers.Main) {
                    showOrders(summary)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RecentOrderActivity, "Failed to fetch orders", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showOrders(summary: OrdersSummary) {
        totalTextView.text = "Period Total: $${String.format("%.2f", summary.overallTotal)}"

        val adapter = object : ArrayAdapter<ProcessedOrder>(this, R.layout.item_recent_order, R.id.orderIdTextView, summary.orders) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: layoutInflater.inflate(R.layout.item_recent_order, parent, false)
                val order = getItem(position)!!

                view.findViewById<TextView>(R.id.orderIdTextView).text = "Order ID: ${order.orderId}"
                view.findViewById<TextView>(R.id.itemsTextView).text = order.items.joinToString("\n") {
                    "${it.name} (x${it.quantity}) - \$${String.format("%.2f", it.unitPrice)}"
                }
                view.findViewById<TextView>(R.id.orderTotalTextView).text = "Order Total: $${String.format("%.2f", order.orderTotal)}"
                return view
            }
        }

        ordersListView.adapter = adapter
    }
}
