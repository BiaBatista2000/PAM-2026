package com.example.feiraapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MapaActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapa)

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }

        val btnPalestras = findViewById<TextView>(R.id.btnPalestras)
        val btnExpositores = findViewById<TextView>(R.id.btnExpositores)
        val btnFoodTruck = findViewById<TextView>(R.id.btnFoodTruck)
        val btnSala2 = findViewById<TextView>(R.id.btnSala2)
        val btnSala3 = findViewById<TextView>(R.id.btnSala3)
        val txtLocalizacao = findViewById<TextView>(R.id.txtLocalizacao)

        btnPalestras.setOnClickListener {
            Toast.makeText(
                this,
                "🎤 Auditório Principal\nPalestras e apresentações da Feira Tecnológica.",
                Toast.LENGTH_LONG
            ).show()
        }

        btnExpositores.setOnClickListener {
            Toast.makeText(
                this,
                "🏢 Área dos Expositores\nEmpresas, projetos e demonstrações de tecnologia.",
                Toast.LENGTH_LONG
            ).show()
        }

        btnFoodTruck.setOnClickListener {
            Toast.makeText(
                this,
                "🍔 Food Truck\nÁrea destinada à alimentação dos visitantes.",
                Toast.LENGTH_LONG
            ).show()
        }

        btnSala2.setOnClickListener {
            Toast.makeText(
                this,
                "🖥 Sala 2\nApresentações, projetos e atividades tecnológicas.",
                Toast.LENGTH_LONG
            ).show()
        }

        btnSala3.setOnClickListener {
            Toast.makeText(
                this,
                "💡 Sala 3\nAtividades, demonstrações e projetos dos participantes.",
                Toast.LENGTH_LONG
            ).show()
        }

        txtLocalizacao.setOnClickListener {
            Toast.makeText(
                this,
                "📍 Localização\nVocê está na área central do evento.\n\n🚻 Sanitários disponíveis próximos ao local.\n✚ Saída de emergência sinalizada.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}