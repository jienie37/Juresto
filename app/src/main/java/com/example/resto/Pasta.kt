package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Pasta : AppCompatActivity() {

    private var quantitySpag = 0
    private var quantityPesto = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pasta)

        // Spaghetti Views
        val txtQuantitySpag = findViewById<TextView>(R.id.txtQuantitySpag)
        val btnIncreaseSpag = findViewById<Button>(R.id.btnIncreaseSpag)
        val btnDecreaseSpag = findViewById<Button>(R.id.btnDecreaseSpag)
        val btnAddToOrderSpag = findViewById<Button>(R.id.btnAddToOrderSpag)

        // Pesto Views
        val txtQuantityPesto = findViewById<TextView>(R.id.txtQuantityPesto)
        val btnIncreasePesto = findViewById<Button>(R.id.btnIncreasePesto)
        val btnDecreasePesto = findViewById<Button>(R.id.btnDecreasePesto)
        val btnAddToOrderPesto = findViewById<Button>(R.id.btnAddToOrderPesto)

        // Spaghetti Logic
        fun updateSpagQuantity() {
            txtQuantitySpag.text = quantitySpag.toString()
        }

        btnIncreaseSpag.setOnClickListener {
            quantitySpag++
            updateSpagQuantity()
        }

        btnDecreaseSpag.setOnClickListener {
            if (quantitySpag > 0) {
                quantitySpag--
                updateSpagQuantity()
            }
        }

        btnAddToOrderSpag.setOnClickListener {
            if (quantitySpag > 0) {
                OrderManager.addItem("Spaghetti", quantitySpag, 50.0)
                Toast.makeText(this, "Spaghetti added to order!", Toast.LENGTH_SHORT).show()
                quantitySpag = 0
                updateSpagQuantity()
            } else {
                Toast.makeText(this, "Select at least 1 Spaghetti", Toast.LENGTH_SHORT).show()
            }
        }

        // Pesto Logic
        fun updatePestoQuantity() {
            txtQuantityPesto.text = quantityPesto.toString()
        }

        btnIncreasePesto.setOnClickListener {
            quantityPesto++
            updatePestoQuantity()
        }

        btnDecreasePesto.setOnClickListener {
            if (quantityPesto > 0) {
                quantityPesto--
                updatePestoQuantity()
            }
        }

        btnAddToOrderPesto.setOnClickListener {
            if (quantityPesto > 0) {
                OrderManager.addItem("Pesto Pasta", quantityPesto, 60.0)
                Toast.makeText(this, "Pesto added to order!", Toast.LENGTH_SHORT).show()
                quantityPesto = 0
                updatePestoQuantity()
            } else {
                Toast.makeText(this, "Select at least 1 Pesto", Toast.LENGTH_SHORT).show()
            }
        }

        // Show Orders Button
        findViewById<Button>(R.id.btnShowOrder).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}
