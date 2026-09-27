package com.example.feiraapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.etEmail)
        val senha = findViewById<EditText>(R.id.etSenha)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val esqueciSenha = findViewById<TextView>(R.id.tvEsqueciSenha)

        btnCadastrar.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        btnEntrar.setOnClickListener {

            val emailTexto = email.text.toString().trim()
            val senhaTexto = senha.text.toString()

            if (emailTexto.isEmpty()) {
                email.error = "Digite seu e-mail"
                return@setOnClickListener
            }

            if (senhaTexto.isEmpty()) {
                senha.error = "Digite sua senha"
                return@setOnClickListener
            }

            btnEntrar.isEnabled = false

            auth.signInWithEmailAndPassword(emailTexto, senhaTexto)
                .addOnCompleteListener { resultado ->

                    btnEntrar.isEnabled = true

                    if (resultado.isSuccessful) {

                        val intent = Intent(this, HomeActivity::class.java)

                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)

                    } else {

                        Toast.makeText(
                            this,
                            "E-mail ou senha incorretos.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        esqueciSenha.setOnClickListener {

            val emailTexto = email.text.toString().trim()

            if (emailTexto.isEmpty()) {
                email.error = "Digite seu e-mail"
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(emailTexto)
                .addOnCompleteListener { resultado ->

                    if (resultado.isSuccessful) {

                        Toast.makeText(
                            this,
                            "E-mail de recuperação enviado.",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {

                        Toast.makeText(
                            this,
                            "Não foi possível enviar o e-mail.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}