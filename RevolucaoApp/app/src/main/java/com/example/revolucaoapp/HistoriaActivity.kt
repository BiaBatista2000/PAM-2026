package com.example.revolucaoapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class HistoriaActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.historia)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarHistoria)

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}