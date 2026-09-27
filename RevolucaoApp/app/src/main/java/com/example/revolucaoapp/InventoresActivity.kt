package com.example.revolucaoapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class InventoresActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventores)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarInventores)

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}