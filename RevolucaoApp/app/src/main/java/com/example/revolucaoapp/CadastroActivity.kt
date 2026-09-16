package com.example.revolucaoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro)

        auth = FirebaseAuth.getInstance()

        val nome = findViewById<EditText>(R.id.nome)
        val email = findViewById<EditText>(R.id.emailCadastro)
        val senha = findViewById<EditText>(R.id.senhaCadastro)
        val confirmarSenha = findViewById<EditText>(R.id.confirmarSenha)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val voltarLogin = findViewById<TextView>(R.id.voltarLogin)

        btnCadastrar.setOnClickListener {
            val nomeDigitado = nome.text.toString().trim()
            val emailDigitado = email.text.toString().trim()
            val senhaDigitada = senha.text.toString()
            val confirmarSenhaDigitada = confirmarSenha.text.toString()

            if (nomeDigitado.isEmpty()) {
                nome.error = "Digite seu nome"
                nome.requestFocus()
                return@setOnClickListener
            }

            if (emailDigitado.isEmpty()) {
                email.error = "Digite seu e-mail"
                email.requestFocus()
                return@setOnClickListener
            }

            if (senhaDigitada.isEmpty()) {
                senha.error = "Digite sua senha"
                senha.requestFocus()
                return@setOnClickListener
            }

            if (senhaDigitada.length < 6) {
                senha.error = "A senha deve ter pelo menos 6 caracteres"
                senha.requestFocus()
                return@setOnClickListener
            }

            if (confirmarSenhaDigitada != senhaDigitada) {
                confirmarSenha.error = "As senhas não coincidem"
                confirmarSenha.requestFocus()
                return@setOnClickListener
            }

            btnCadastrar.isEnabled = false

            auth.createUserWithEmailAndPassword(emailDigitado, senhaDigitada)
                .addOnCompleteListener { task ->
                    btnCadastrar.isEnabled = true

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Cadastro realizado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Não foi possível realizar o cadastro.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        voltarLogin.setOnClickListener {
            finish()
        }
    }
}