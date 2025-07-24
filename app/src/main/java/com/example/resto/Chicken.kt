package com.example.resto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Chicken : AppCompatActivity() {

    private var quantityGrilled = 0
    private var quantityFried = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chicken)

        val txtQuantityGrilled = findViewById<TextView>(R.id.txtQuantityGrilled)
        val btnIncreaseGrilled = findViewById<Button>(R.id.btnIncreaseGrilled)
        val btnDecreaseGrilled = findViewById<Button>(R.id.btnDecreaseGrilled)
        val btnAddToOrderGrilled = findViewById<Button>(R.id.btnAddToOrderGrilled)

        val txtQuantityFried = findViewById<TextView>(R.id.txtQuantity)
        val btnIncreaseFried = findViewById<Button>(R.id.btnIncrease)
        val btnDecreaseFried = findViewById<Button>(R.id.btnDecrease)
        val btnAddToOrderFried = findViewById<Button>(R.id.btnAddToOrder)

        fun updateFriedQuantityText() {
            txtQuantityFried.text = quantityFried.toString()
        }

        btnIncreaseFried.setOnClickListener {
            quantityFried++
            updateFriedQuantityText()
        }

        btnDecreaseFried.setOnClickListener {
            if (quantityFried > 0) {
                quantityFried--
                updateFriedQuantityText()
            }
        }

        btnAddToOrderFried.setOnClickListener {
            if (quantityFried > 0) {
                OrderManager.addItem("Fried Chicken", quantityFried, 75.0)
                Toast.makeText(this, "Added to order!", Toast.LENGTH_SHORT).show()
                quantityFried = 0
                updateFriedQuantityText()
            } else {
                Toast.makeText(this, "Select at least 1 fried chicken", Toast.LENGTH_SHORT).show()
            }
        }


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
                OrderManager.addItem("Grilled Chicken", quantityGrilled, 85.0)
                Toast.makeText(this, "Added to order!", Toast.LENGTH_SHORT).show()
                quantityGrilled = 0
                updateGrilledQuantityText()
            } else {
                Toast.makeText(this, "Select at least 1 grilled chicken", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnShowOrder).setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}
