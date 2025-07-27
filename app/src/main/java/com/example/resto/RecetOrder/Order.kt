import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class OrderItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val unitPrice: Double
)

data class ProcessedOrder(
    val orderId: String,
    val items: List<OrderItem>,
    val orderTimestamp: Long
) {
    val orderTotal: Double
        get() = items.sumOf { it.quantity * it.unitPrice }

    val formattedDate: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            return sdf.format(Date(orderTimestamp))
        }
}

data class OrdersSummary(
    val orders: List<ProcessedOrder>,
    val overallTotal: Double
)
