package com.example.feiraapp

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class FavoritosActivity : Activity() {

    private lateinit var containerLista: LinearLayout

    private val palestras = listOf(
        Triple(
            "Inteligência Artificial no Dia a Dia",
            "10:00 - Auditório Principal",
            "Descubra como a Inteligência Artificial está presente em atividades do dia a dia e como ela pode transformar diferentes áreas da sociedade."
        ),
        Triple(
            "Desenvolvimento Mobile com Flutter",
            "13:00 - Sala 2",
            "Conheça conceitos de desenvolvimento de aplicativos móveis utilizando Flutter e veja como criar aplicações para diferentes plataformas."
        ),
        Triple(
            "Segurança da Informação",
            "15:00 - Auditório Principal",
            "Aprenda conceitos importantes sobre segurança digital, proteção de dados, senhas seguras e prevenção contra ameaças virtuais."
        ),
        Triple(
            "O Futuro da Tecnologia",
            "17:00 - Sala 3",
            "Uma conversa sobre novas tecnologias, inovação, inteligência artificial, sustentabilidade e as transformações que podem acontecer nos próximos anos."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favoritos)

        containerLista = findViewById(R.id.containerLista)

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            finish()
        }

        mostrarFavoritos()
    }

    override fun onResume() {
        super.onResume()

        if (::containerLista.isInitialized) {
            mostrarFavoritos()
        }
    }

    private fun mostrarFavoritos() {

        containerLista.removeAllViews()

        val favoritos = getSharedPreferences(
            "FeiraApp",
            MODE_PRIVATE
        )

        val listaFavoritos = palestras.filter {
            favoritos.getBoolean(it.first, false)
        }

        if (listaFavoritos.isEmpty()) {

            val vazio = TextView(this).apply {
                text = "☆\n\nVocê ainda não possui favoritos.\n\nVolte para Palestras e adicione suas palestras favoritas."
                textSize = 17f
                setTextColor(Color.rgb(205, 215, 225))
                gravity = Gravity.CENTER
                setPadding(20, 50, 20, 50)
            }

            containerLista.addView(vazio)

            return
        }

        listaFavoritos.forEach { palestra ->

            val titulo = palestra.first
            val horario = palestra.second
            val descricao = palestra.third

            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(22, 20, 22, 20)

                background = GradientDrawable().apply {
                    setColor(Color.rgb(28, 36, 48))
                    cornerRadius = 24f
                }

                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 16)
                }
            }

            val tituloTexto = TextView(this).apply {
                text = "★  $titulo"
                textSize = 20f
                setTextColor(Color.WHITE)
                setTypeface(null, Typeface.BOLD)
            }

            val horarioTexto = TextView(this).apply {
                text = horario
                textSize = 15f
                setTextColor(Color.rgb(130, 190, 255))
                setTypeface(null, Typeface.BOLD)
                setPadding(0, 10, 0, 10)
            }

            val descricaoTexto = TextView(this).apply {
                text = descricao
                textSize = 15f
                setTextColor(Color.rgb(195, 205, 218))
                setPadding(0, 0, 0, 14)
            }

            val btnRemover = Button(this).apply {
                text = "★  Remover dos favoritos"
                setAllCaps(false)
                setTextColor(Color.WHITE)

                backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        Color.rgb(60, 100, 150)
                    )
            }

            btnRemover.setOnClickListener {

                favoritos.edit()
                    .putBoolean(titulo, false)
                    .apply()

                mostrarFavoritos()
            }

            card.addView(tituloTexto)
            card.addView(horarioTexto)
            card.addView(descricaoTexto)
            card.addView(btnRemover)

            containerLista.addView(card)
        }
    }
}