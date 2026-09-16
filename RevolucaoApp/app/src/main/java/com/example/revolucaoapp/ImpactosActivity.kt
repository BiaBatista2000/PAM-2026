package com.example.revolucaoapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class ImpactosActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.impactos)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarImpactos)

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}