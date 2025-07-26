package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Drinks : AppCompatActivity() {

    private var quantityTea = 0
    private var quantityCoffee = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drinks)

        // Iced Tea Views
        val txtQuantityTea = findViewById<TextView>(R.id.txtQuantityIceTea)
        val btnIncreaseTea = findViewById<Button>(R.id.btnIncreaseIceTea)
        val btnDecreaseTea = findViewById<Button>(R.id.btnDecreaseIceTea)
        val btnAddToOrderTea = findViewById<Button>(R.id.btnAddToOrderIceTea)

        // Coffee Views
        val txtQuantityCoffee = findViewById<TextView>(R.id.txtQuantityCoffee)
        val btnIncreaseCoffee = findViewById<Button>(R.id.btnIncreaseCoffee)
        val btnDecreaseCoffee = findViewById<Button>(R.id.btnDecreaseCoffee)
        val btnAddToOrderCoffee = findViewById<Button>(R.id.btnAddToOrderCoffee)

        // --- Quantity Update Functions ---
        fun updateTeaQuantity() {
            txtQuantityTea.text = quantityTea.toString()
        }

        fun updateCoffeeQuantity() {
            txtQuantityCoffee.text = quantityCoffee.toString()
        }

        // --- Iced Tea Logic ---
        btnIncreaseTea.setOnClickListener {
            quantityTea++
            updateTeaQuantity()
        }

        btnDecreaseTea.setOnClickListener {
            if (quantityTea > 0) {
                quantityTea--
                updateTeaQuantity()
            }
        }

        btnAddToOrderTea.setOnClickListener {
            if (quantityTea > 0) {
                OrderManager.addItem("Iced Tea", quantityTea, 30.0)
                Toast.makeText(this, "Iced Tea added to order!", Toast.LENGTH_SHORT).show()
                quantityTea = 0
                updateTeaQuantity()
            } else {
                Toast.makeText(this, "Please select at least 1 Iced Tea", Toast.LENGTH_SHORT).show()
            }
        }

        // --- Coffee Logic ---
        btnIncreaseCoffee.setOnClickListener {
            quantityCoffee++
            updateCoffeeQuantity()
        }

        btnDecreaseCoffee.setOnClickListener {
            if (quantityCoffee > 0) {
                quantityCoffee--
                updateCoffeeQuantity()
            }
        }

        btnAddToOrderCoffee.setOnClickListener {
            if (quantityCoffee > 0) {
                OrderManager.addItem("Coffee", quantityCoffee, 40.0) // Adjust price if needed
                Toast.makeText(this, "Coffee added to order!", Toast.LENGTH_SHORT).show()
                quantityCoffee = 0
                updateCoffeeQuantity()
            } else {
                Toast.makeText(this, "Please select at least 1 Coffee", Toast.LENGTH_SHORT).show()
            }
        }

        // Show Order returns to MenuActivity
        findViewById<Button>(R.id.btnShowOrder).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        // Category switching buttons
        findViewById<Button>(R.id.btnChicken).setOnClickListener {
            val intent = Intent(this, Chicken::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnSides).setOnClickListener {
            val intent = Intent(this, Sides::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnPasta).setOnClickListener {
            val intent = Intent(this, Pasta::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnDrinks).setOnClickListener {
            val intent = Intent(this, Drinks::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}
