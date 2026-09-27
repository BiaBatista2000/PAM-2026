package com.example.revolucaoapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class FasesActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fases)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarFases)

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}