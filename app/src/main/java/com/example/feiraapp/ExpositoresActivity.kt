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

class ExpositoresActivity : Activity() {

    private lateinit var containerLista: LinearLayout
    private lateinit var campoBusca: EditText

    private val expositores = listOf(
        Triple(
            "TechSolutions",
            "Soluções em software, aplicativos e tecnologia.",
            "A TechSolutions apresenta soluções modernas para desenvolvimento de sistemas, aplicativos móveis e plataformas digitais."
        ),
        Triple(
            "FutureTech",
            "Inovação em hardware, dispositivos e novas tecnologias.",
            "A FutureTech trabalha com novas tecnologias, dispositivos inteligentes, automação e soluções para o futuro."
        ),
        Triple(
            "CodeLab",
            "Cursos, capacitação e desenvolvimento profissional.",
            "A CodeLab oferece cursos e projetos voltados para programação, desenvolvimento de sistemas e capacitação profissional."
        ),
        Triple(
            "GreenTech",
            "Sustentabilidade, tecnologia verde e inovação ambiental.",
            "A GreenTech desenvolve soluções tecnológicas voltadas para sustentabilidade, preservação ambiental e uso consciente dos recursos."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expositores)

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        campoBusca = findViewById(R.id.edtBusca)

        containerLista = findViewById(R.id.containerLista)

        btnVoltar.setOnClickListener {
            finish()
        }

        mostrarExpositores(expositores)

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

                val pesquisa = s.toString()
                    .trim()
                    .lowercase()

                val resultados = expositores.filter { expositor ->

                    expositor.first
                        .lowercase()
                        .contains(pesquisa) ||

                            expositor.second
                                .lowercase()
                                .contains(pesquisa) ||

                            expositor.third
                                .lowercase()
                                .contains(pesquisa)
                }

                mostrarExpositores(resultados)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun mostrarExpositores(
        lista: List<Triple<String, String, String>>
    ) {

        containerLista.removeAllViews()

        if (lista.isEmpty()) {

            val vazio = TextView(this).apply {

                text = "Nenhum expositor encontrado."

                textSize = 17f

                setTextColor(Color.WHITE)

                setPadding(
                    16,
                    30,
                    16,
                    30
                )
            }

            containerLista.addView(vazio)

            return
        }

        lista.forEach { expositor ->

            val nome = expositor.first
            val descricao = expositor.second
            val detalhes = expositor.third

            val card = LinearLayout(this).apply {

                orientation = LinearLayout.VERTICAL

                setPadding(
                    22,
                    20,
                    22,
                    20
                )

                background = GradientDrawable().apply {

                    setColor(
                        Color.rgb(
                            28,
                            36,
                            48
                        )
                    )

                    cornerRadius = 24f
                }

                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {

                    setMargins(
                        0,
                        0,
                        0,
                        16
                    )
                }
            }

            val titulo = TextView(this).apply {

                text = nome

                textSize = 20f

                setTextColor(Color.WHITE)

                setTypeface(
                    null,
                    Typeface.BOLD
                )
            }

            val descricaoTexto = TextView(this).apply {

                text = descricao

                textSize = 15f

                setTextColor(
                    Color.rgb(
                        205,
                        215,
                        225
                    )
                )

                setPadding(
                    0,
                    10,
                    0,
                    14
                )
            }

            val areaDetalhes = LinearLayout(this).apply {

                orientation = LinearLayout.VERTICAL

                setPadding(
                    18,
                    16,
                    18,
                    16
                )

                background = GradientDrawable().apply {

                    setColor(
                        Color.rgb(
                            18,
                            24,
                            34
                        )
                    )

                    cornerRadius = 18f
                }

                visibility = View.GONE

                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {

                    setMargins(
                        0,
                        4,
                        0,
                        14
                    )
                }
            }

            val tituloDetalhes = TextView(this).apply {

                text = "Sobre o expositor"

                textSize = 17f

                setTextColor(Color.WHITE)

                setTypeface(
                    null,
                    Typeface.BOLD
                )
            }

            val textoDetalhes = TextView(this).apply {

                text = detalhes

                textSize = 15f

                setTextColor(
                    Color.rgb(
                        195,
                        205,
                        218
                    )
                )

                setPadding(
                    0,
                    8,
                    0,
                    0
                )
            }

            areaDetalhes.addView(tituloDetalhes)

            areaDetalhes.addView(textoDetalhes)

            val btnDetalhes = Button(this).apply {

                text = "Ver detalhes"

                isAllCaps = false

                setTextColor(Color.WHITE)

                backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        Color.rgb(
                            60,
                            100,
                            150
                        )
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

            card.addView(titulo)

            card.addView(descricaoTexto)

            card.addView(areaDetalhes)

            card.addView(btnDetalhes)

            containerLista.addView(card)
        }
    }
}