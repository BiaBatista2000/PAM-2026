package com.example.revolucaoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        val btnHistoria = findViewById<Button>(R.id.btnHistoria)
        val btnFases = findViewById<Button>(R.id.btnFases)
        val btnInventores = findViewById<Button>(R.id.btnInventores)
        val btnImpactos = findViewById<Button>(R.id.btnImpactos)

        btnHistoria.setOnClickListener {
            startActivity(Intent(this, HistoriaActivity::class.java))
        }

        btnFases.setOnClickListener {
            startActivity(Intent(this, FasesActivity::class.java))
        }

        btnInventores.setOnClickListener {
            startActivity(Intent(this, InventoresActivity::class.java))
        }

        btnImpactos.setOnClickListener {
            startActivity(Intent(this, ImpactosActivity::class.java))
        }
    }
}