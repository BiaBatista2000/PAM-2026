package com.example.feiraapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class CadastroActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro)

        auth = FirebaseAuth.getInstance()

        val nome = findViewById<EditText>(R.id.etNome)
        val email = findViewById<EditText>(R.id.etEmail)
        val senha = findViewById<EditText>(R.id.etSenha)
        val confirmarSenha = findViewById<EditText>(R.id.etConfirmarSenha)

        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnCadastrar.setOnClickListener {

            val nomeTexto = nome.text.toString().trim()
            val emailTexto = email.text.toString().trim()
            val senhaTexto = senha.text.toString()
            val confirmarTexto = confirmarSenha.text.toString()

            if (nomeTexto.isEmpty()) {
                nome.error = "Digite seu nome"
                nome.requestFocus()
                return@setOnClickListener
            }

            if (emailTexto.isEmpty()) {
                email.error = "Digite seu e-mail"
                email.requestFocus()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailTexto).matches()) {
                email.error = "Digite um e-mail válido"
                email.requestFocus()
                return@setOnClickListener
            }

            if (senhaTexto.length < 6) {
                senha.error = "A senha deve ter pelo menos 6 caracteres"
                senha.requestFocus()
                return@setOnClickListener
            }

            if (senhaTexto != confirmarTexto) {
                confirmarSenha.error = "As senhas não são iguais"
                confirmarSenha.requestFocus()
                return@setOnClickListener
            }

            btnCadastrar.isEnabled = false

            auth.createUserWithEmailAndPassword(
                emailTexto,
                senhaTexto
            ).addOnCompleteListener { resultado ->

                if (resultado.isSuccessful) {

                    val usuario = auth.currentUser

                    if (usuario != null) {

                        val perfil = UserProfileChangeRequest.Builder()
                            .setDisplayName(nomeTexto)
                            .build()

                        usuario.updateProfile(perfil)
                            .addOnCompleteListener {

                                auth.signOut()

                                Toast.makeText(
                                    this,
                                    "Conta criada com sucesso! Faça seu login.",
                                    Toast.LENGTH_LONG
                                ).show()

                                val intent = Intent(
                                    this,
                                    MainActivity::class.java
                                )

                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK

                                startActivity(intent)
                            }

                    } else {

                        btnCadastrar.isEnabled = true

                        Toast.makeText(
                            this,
                            "Não foi possível criar a conta.",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                } else {

                    btnCadastrar.isEnabled = true

                    val erro = resultado.exception?.message ?: ""

                    val mensagem = when {

                        erro.contains(
                            "email address is already in use",
                            true
                        ) ->
                            "Este e-mail já está cadastrado."

                        erro.contains(
                            "email-already-in-use",
                            true
                        ) ->
                            "Este e-mail já está cadastrado."

                        erro.contains(
                            "password is too weak",
                            true
                        ) ->
                            "A senha deve ter pelo menos 6 caracteres."

                        erro.contains(
                            "invalid-email",
                            true
                        ) ->
                            "Digite um e-mail válido."

                        else ->
                            "Erro ao criar conta."
                    }

                    Toast.makeText(
                        this,
                        mensagem,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        tvLogin.setOnClickListener {
            finish()
        }
    }
}