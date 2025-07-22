package com.example.resto.RecetOrder
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


class RecentOrderActivity : AppCompatActivity() {

    private lateinit var ordersListView: ListView
    private lateinit var totalTextView: TextView

    private val dailyOrdersSummary = OrdersSummary(
        orders = listOf(
            ProcessedOrder("D001", listOf(OrderItem("item1", "Pizza", 1, 12.99)), System.currentTimeMillis()),
            ProcessedOrder("D002", listOf(OrderItem("item2", "Coke", 2, 1.50), OrderItem("item3", "Fries", 1, 3.00)), System.currentTimeMillis() - 100000)
        ),
        overallTotal = 12.99 + (2 * 1.50) + 3.00
    )

    private val weeklyOrdersSummary = OrdersSummary(
        orders = listOf(
            ProcessedOrder("W001", listOf(OrderItem("itemW1", "Burger", 2, 5.50)), System.currentTimeMillis() - 86400000L * 2),
            ProcessedOrder("W002", listOf(OrderItem("itemW2", "Salad", 1, 7.25), OrderItem("itemW3", "Water", 1, 1.00)), System.currentTimeMillis() - 86400000L * 3)
        ),
        overallTotal = (2 * 5.50) + 7.25 + 1.00
    )

    private val monthlyOrdersSummary = OrdersSummary(emptyList(), 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_order)

        ordersListView = findViewById(R.id.ordersListView)
        totalTextView = findViewById(R.id.totalTextView)

        val tabGroup = findViewById<RadioGroup>(R.id.tabGroup)
        val closeButton = findViewById<Button>(R.id.closeButton)

        tabGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.tabDaily -> showOrders(dailyOrdersSummary)
                R.id.tabWeekly -> showOrders(weeklyOrdersSummary)
                R.id.tabMonthly -> showOrders(monthlyOrdersSummary)
            }
        }

        closeButton.setOnClickListener {
            finish() // Closes the activity
        }

        // Show default
        showOrders(dailyOrdersSummary)
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
