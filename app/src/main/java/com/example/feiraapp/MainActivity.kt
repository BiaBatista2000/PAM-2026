package com.example.feiraapp

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
                email.requestFocus()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailTexto).matches()) {
                email.error = "Digite um e-mail válido"
                email.requestFocus()
                return@setOnClickListener
            }

            if (senhaTexto.isEmpty()) {
                senha.error = "Digite sua senha"
                senha.requestFocus()
                return@setOnClickListener
            }

            btnEntrar.isEnabled = false

            auth.signInWithEmailAndPassword(emailTexto, senhaTexto)
                .addOnCompleteListener { resultado ->

                    btnEntrar.isEnabled = true

                    if (resultado.isSuccessful) {

                        Toast.makeText(
                            this,
                            "Login realizado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)

                    } else {

                        val erro = resultado.exception?.message ?: ""

                        val mensagem = when {
                            erro.contains("no user record", true) ->
                                "Este e-mail não está cadastrado."

                            erro.contains("password is invalid", true) ->
                                "Senha incorreta."

                            erro.contains("invalid credential", true) ->
                                "E-mail ou senha incorretos."

                            erro.contains("user disabled", true) ->
                                "Esta conta está desativada."

                            else ->
                                "E-mail ou senha incorretos."
                        }

                        Toast.makeText(
                            this,
                            mensagem,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        esqueciSenha.setOnClickListener {

            val emailTexto = email.text.toString().trim()

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