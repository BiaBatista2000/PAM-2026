package com.example.revolucaoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.email)
        val senha = findViewById<EditText>(R.id.senha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val cadastro = findViewById<TextView>(R.id.cadastro)
        val esqueciSenha = findViewById<TextView>(R.id.esqueciSenha)

        btnEntrar.setOnClickListener {
            val emailDigitado = email.text.toString().trim()
            val senhaDigitada = senha.text.toString()

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

            btnEntrar.isEnabled = false

            auth.signInWithEmailAndPassword(emailDigitado, senhaDigitada)
                .addOnCompleteListener { task ->
                    btnEntrar.isEnabled = true

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Login realizado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "E-mail ou senha incorretos.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        cadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        esqueciSenha.setOnClickListener {
            enviarRecuperacaoSenha(email)
        }
    }

    private fun enviarRecuperacaoSenha(email: EditText) {
        val emailDigitado = email.text.toString().trim()

        if (emailDigitado.isEmpty()) {
            email.error = "Digite seu e-mail"
            email.requestFocus()
            return
        }

        auth.sendPasswordResetEmail(emailDigitado)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "E-mail de recuperação enviado!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Não foi possível enviar o e-mail de recuperação.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}