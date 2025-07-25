package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Sides : AppCompatActivity() {

    private var quantityBurger = 0
    private var quantityFries = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sides)

        // Burger Views
        val txtQuantityBurger = findViewById<TextView>(R.id.txtQuantityBurger)
        val btnIncreaseBurger = findViewById<Button>(R.id.btnIncreaseBurger)
        val btnDecreaseBurger = findViewById<Button>(R.id.btnDecreaseBurger)
        val btnAddToOrderBurger = findViewById<Button>(R.id.btnAddToOrderBurger)

        // Fries Views
        val txtQuantityFries = findViewById<TextView>(R.id.txtQuantityFries)
        val btnIncreaseFries = findViewById<Button>(R.id.btnIncreaseFries)
        val btnDecreaseFries = findViewById<Button>(R.id.btnDecreaseFries)
        val btnAddToOrderFries = findViewById<Button>(R.id.btnAddToOrderFries)

        // Burger Logic
        fun updateBurgerQuantity() {
            txtQuantityBurger.text = quantityBurger.toString()
        }

        btnIncreaseBurger.setOnClickListener {
            quantityBurger++
            updateBurgerQuantity()
        }

        btnDecreaseBurger.setOnClickListener {
            if (quantityBurger > 0) {
                quantityBurger--
                updateBurgerQuantity()
            }
        }

        btnAddToOrderBurger.setOnClickListener {
            if (quantityBurger > 0) {
                OrderManager.addItem("Hamburger", quantityBurger, 45.0)
                Toast.makeText(this, "Hamburger added to order!", Toast.LENGTH_SHORT).show()
                quantityBurger = 0
                updateBurgerQuantity()
            } else {
                Toast.makeText(this, "Select at least 1 Hamburger", Toast.LENGTH_SHORT).show()
            }
        }

        // Fries Logic
        fun updateFriesQuantity() {
            txtQuantityFries.text = quantityFries.toString()
        }

        btnIncreaseFries.setOnClickListener {
            quantityFries++
            updateFriesQuantity()
        }

        btnDecreaseFries.setOnClickListener {
            if (quantityFries > 0) {
                quantityFries--
                updateFriesQuantity()
            }
        }

        btnAddToOrderFries.setOnClickListener {
            if (quantityFries > 0) {
                OrderManager.addItem("French Fries", quantityFries, 30.0)
                Toast.makeText(this, "Fries added to order!", Toast.LENGTH_SHORT).show()
                quantityFries = 0
                updateFriesQuantity()
            } else {
                Toast.makeText(this, "Select at least 1 Fries", Toast.LENGTH_SHORT).show()
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
