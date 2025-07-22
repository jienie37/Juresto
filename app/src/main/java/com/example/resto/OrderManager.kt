package com.example.resto

object OrderManager {
    private val orderList = mutableListOf<String>()

    fun addItem(item: String) {
        orderList.add(item)
    }

    fun getOrders(): List<String> {
        return orderList.toList()
    }

    fun clearOrders() {
        orderList.clear()
    }
}
