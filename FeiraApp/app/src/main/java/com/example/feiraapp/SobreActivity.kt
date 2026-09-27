package com.example.feiraapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class SobreActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sobre)

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            finish()
        }
    }
}