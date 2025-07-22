package com.example.resto.RecetOrder

// OrderItem.kt
data class OrderItem(val id: String, val name: String, val quantity: Int, val unitPrice: Double)

// ProcessedOrder.kt
data class ProcessedOrder(val orderId: String, val items: List<OrderItem>, val timestamp: Long) {
    val orderTotal: Double get() = items.sumOf { it.quantity * it.unitPrice }
}

// OrdersSummary.kt
data class OrdersSummary(val orders: List<ProcessedOrder>, val overallTotal: Double)
