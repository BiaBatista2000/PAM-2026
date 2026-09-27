package com.example.feiraapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        findViewById<Button>(R.id.btnPalestras).setOnClickListener {
            startActivity(Intent(this, PalestrasActivity::class.java))
        }

        findViewById<Button>(R.id.btnExpositores).setOnClickListener {
            startActivity(Intent(this, ExpositoresActivity::class.java))
        }

        findViewById<Button>(R.id.btnProgramacao).setOnClickListener {
            startActivity(Intent(this, ProgramacaoActivity::class.java))
        }

        findViewById<Button>(R.id.btnMapa).setOnClickListener {
            startActivity(Intent(this, MapaActivity::class.java))
        }

        findViewById<Button>(R.id.btnFavoritos).setOnClickListener {
            startActivity(Intent(this, FavoritosActivity::class.java))
        }

        findViewById<Button>(R.id.btnSobre).setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))
        }

        findViewById<Button>(R.id.btnSair).setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}