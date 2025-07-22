package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Chicken : AppCompatActivity() {

    private var quantityFried = 0
    private var quantityGrilled = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chicken)

        // Fried Chicken Views
        val txtQuantity = findViewById<TextView>(R.id.txtQuantity)
        val btnIncrease = findViewById<Button>(R.id.btnIncrease)
        val btnDecrease = findViewById<Button>(R.id.btnDecrease)
        val btnAddToOrder = findViewById<Button>(R.id.btnAddToOrder)

        // Grilled Chicken Views
        val txtQuantityGrilled = findViewById<TextView>(R.id.txtQuantityGrilled)
        val btnIncreaseGrilled = findViewById<Button>(R.id.btnIncreaseGrilled)
        val btnDecreaseGrilled = findViewById<Button>(R.id.btnDecreaseGrilled)
        val btnAddToOrderGrilled = findViewById<Button>(R.id.btnAddToOrderGrilled)

        // Fried Chicken Logic
        fun updateFriedQuantityText() {
            txtQuantity.text = quantityFried.toString()
        }

        btnIncrease.setOnClickListener {
            quantityFried++
            updateFriedQuantityText()
        }

        btnDecrease.setOnClickListener {
            if (quantityFried > 0) {
                quantityFried--
                updateFriedQuantityText()
            }
        }

        btnAddToOrder.setOnClickListener {
            if (quantityFried > 0) {
                OrderManager.addItem("$quantityFried Fried Chicken(s)")
                Toast.makeText(this, "Added to order!", Toast.LENGTH_SHORT).show()
                quantityFried = 0
                updateFriedQuantityText()
            } else {
                Toast.makeText(this, "Select at least 1 fried chicken", Toast.LENGTH_SHORT).show()
            }
        }

        // Grilled Chicken Logic
        fun updateGrilledQuantityText() {
            txtQuantityGrilled.text = quantityGrilled.toString()
        }

        btnIncreaseGrilled.setOnClickListener {
            quantityGrilled++
            updateGrilledQuantityText()
        }

        btnDecreaseGrilled.setOnClickListener {
            if (quantityGrilled > 0) {
                quantityGrilled--
                updateGrilledQuantityText()
            }
        }

        btnAddToOrderGrilled.setOnClickListener {
            if (quantityGrilled > 0) {
                OrderManager.addItem("$quantityGrilled Grilled Chicken(s)")
                Toast.makeText(this, "Added to order!", Toast.LENGTH_SHORT).show()
                quantityGrilled = 0
                updateGrilledQuantityText()
            } else {
                Toast.makeText(this, "Select at least 1 grilled chicken", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnShowOrder).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }



    }
}
