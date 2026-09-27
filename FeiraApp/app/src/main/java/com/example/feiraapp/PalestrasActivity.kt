package com.example.feiraapp

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class PalestrasActivity : Activity() {

    private lateinit var containerLista: LinearLayout
    private lateinit var campoBusca: EditText

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
        setContentView(R.layout.palestras)

        containerLista = findViewById(R.id.containerLista)
        campoBusca = findViewById(R.id.edtBusca)

        findViewById<Button>(R.id.btnVoltar).setOnClickListener {
            finish()
        }

        mostrarPalestras(palestras)

        campoBusca.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                val pesquisa = s.toString().trim().lowercase()

                val resultados = palestras.filter {
                    it.first.lowercase().contains(pesquisa) ||
                            it.second.lowercase().contains(pesquisa) ||
                            it.third.lowercase().contains(pesquisa)
                }

                mostrarPalestras(resultados)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun mostrarPalestras(
        lista: List<Triple<String, String, String>>
    ) {
        containerLista.removeAllViews()

        if (lista.isEmpty()) {
            val vazio = TextView(this).apply {
                text = "Nenhuma palestra encontrada."
                textSize = 17f
                setTextColor(Color.WHITE)
                setPadding(16, 30, 16, 30)
            }

            containerLista.addView(vazio)
            return
        }

        lista.forEach { palestra ->

            val tituloPalestra = palestra.first
            val horarioLocal = palestra.second
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

            val titulo = TextView(this).apply {
                text = tituloPalestra
                textSize = 20f
                setTextColor(Color.WHITE)
                setTypeface(null, Typeface.BOLD)
            }

            val horario = TextView(this).apply {
                text = horarioLocal
                textSize = 15f
                setTextColor(Color.rgb(130, 190, 255))
                setTypeface(null, Typeface.BOLD)
                setPadding(0, 10, 0, 8)
            }

            val areaDetalhes = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(18, 16, 18, 16)

                background = GradientDrawable().apply {
                    setColor(Color.rgb(18, 24, 34))
                    cornerRadius = 18f
                }

                visibility = View.GONE

                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 4, 0, 14)
                }
            }

            val tituloDetalhes = TextView(this).apply {
                text = "Sobre a palestra"
                textSize = 17f
                setTextColor(Color.WHITE)
                setTypeface(null, Typeface.BOLD)
            }

            val textoDetalhes = TextView(this).apply {
                text = descricao
                textSize = 15f
                setTextColor(Color.rgb(195, 205, 218))
                setPadding(0, 8, 0, 0)
            }

            areaDetalhes.addView(tituloDetalhes)
            areaDetalhes.addView(textoDetalhes)

            val btnDetalhes = Button(this).apply {
                text = "Ver detalhes"
                setAllCaps(false)
                setTextColor(Color.WHITE)
                backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        Color.rgb(60, 100, 150)
                    )
            }

            btnDetalhes.setOnClickListener {
                if (areaDetalhes.visibility == View.GONE) {
                    areaDetalhes.visibility = View.VISIBLE
                    btnDetalhes.text = "Ocultar detalhes"
                } else {
                    areaDetalhes.visibility = View.GONE
                    btnDetalhes.text = "Ver detalhes"
                }
            }

            val btnFavorito = Button(this).apply {
                text = "☆  Adicionar aos favoritos"
                setAllCaps(false)
                setTextColor(Color.WHITE)
                backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        Color.rgb(60, 100, 150)
                    )
            }

            btnFavorito.setOnClickListener {

                val favoritos = getSharedPreferences(
                    "FeiraApp",
                    MODE_PRIVATE
                )

                val estaFavorito = favoritos.getBoolean(
                    tituloPalestra,
                    false
                )

                favoritos.edit()
                    .putBoolean(
                        tituloPalestra,
                        !estaFavorito
                    )
                    .apply()

                if (estaFavorito) {
                    btnFavorito.text = "☆  Adicionar aos favoritos"

                    Toast.makeText(
                        this,
                        "Removido dos favoritos",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    btnFavorito.text = "★  Remover dos favoritos"

                    Toast.makeText(
                        this,
                        "Adicionado aos favoritos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            val favoritos = getSharedPreferences(
                "FeiraApp",
                MODE_PRIVATE
            )

            if (favoritos.getBoolean(tituloPalestra, false)) {
                btnFavorito.text = "★  Remover dos favoritos"
            }

            card.addView(titulo)
            card.addView(horario)
            card.addView(areaDetalhes)
            card.addView(btnDetalhes)
            card.addView(btnFavorito)

            containerLista.addView(card)
        }
    }
}