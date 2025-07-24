package com.example.resto

data class OrderItem(val itemName: String, val quantity: Int, val price: Double)

object OrderManager {
    private val orders = mutableListOf<OrderItem>()

    fun addItem(itemName: String, quantity: Int, price: Double) {
        orders.add(OrderItem(itemName, quantity, price))
    }

    fun getOrders(): List<OrderItem> = orders
    fun removeItem(name: String) {
        orders.removeIf { it.itemName.equals(name, ignoreCase = true) }
    }


    fun clearOrders() {
        orders.clear()
    }
}


