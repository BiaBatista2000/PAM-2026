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

class ProgramacaoActivity : Activity() {

    private lateinit var containerLista: LinearLayout
    private lateinit var campoBusca: EditText

    private val eventos = listOf(
        Triple(
            "09:00 - Abertura do Evento",
            "Auditório Principal",
            "Abertura oficial da Feira Tecnológica com apresentação da programação, apresentação dos participantes e orientações sobre as atividades do evento."
        ),
        Triple(
            "10:00 - Inteligência Artificial no Dia a Dia",
            "Auditório Principal",
            "Apresentação sobre o uso da Inteligência Artificial em atividades do cotidiano, estudos, trabalho e desenvolvimento de novas tecnologias."
        ),
        Triple(
            "13:00 - Desenvolvimento Mobile com Flutter",
            "Sala 2",
            "Palestra sobre desenvolvimento de aplicativos móveis utilizando Flutter, apresentando conceitos, ferramentas e possibilidades para criação de aplicativos."
        ),
        Triple(
            "15:00 - Segurança da Informação",
            "Auditório Principal",
            "Conteúdo sobre proteção de dados, segurança digital, senhas, privacidade e cuidados necessários para utilizar a tecnologia de forma segura."
        ),
        Triple(
            "17:00 - O Futuro da Tecnologia",
            "Sala 3",
            "Discussão sobre inovação, novas tecnologias, Inteligência Artificial, sustentabilidade e as possíveis transformações tecnológicas dos próximos anos."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.programacao)

        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        campoBusca = findViewById(R.id.edtBusca)

        containerLista = findViewById(R.id.containerLista)

        btnVoltar.setOnClickListener {
            finish()
        }

        mostrarEventos(eventos)

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

                val resultados = eventos.filter { evento ->

                    evento.first
                        .lowercase()
                        .contains(pesquisa) ||

                            evento.second
                                .lowercase()
                                .contains(pesquisa) ||

                            evento.third
                                .lowercase()
                                .contains(pesquisa)
                }

                mostrarEventos(resultados)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun mostrarEventos(
        lista: List<Triple<String, String, String>>
    ) {

        containerLista.removeAllViews()

        if (lista.isEmpty()) {

            val vazio = TextView(this).apply {

                text = "Nenhum evento encontrado."

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

        lista.forEach { evento ->

            val horarioNome = evento.first
            val local = evento.second
            val descricao = evento.third

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

                text = horarioNome

                textSize = 19f

                setTextColor(Color.WHITE)

                setTypeface(
                    null,
                    Typeface.BOLD
                )
            }

            val localTexto = TextView(this).apply {

                text = local

                textSize = 15f

                setTextColor(
                    Color.rgb(
                        130,
                        190,
                        255
                    )
                )

                setTypeface(
                    null,
                    Typeface.BOLD
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

                text = "Sobre o evento"

                textSize = 17f

                setTextColor(Color.WHITE)

                setTypeface(
                    null,
                    Typeface.BOLD
                )
            }

            val textoDetalhes = TextView(this).apply {

                text = descricao

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

            card.addView(localTexto)

            card.addView(areaDetalhes)

            card.addView(btnDetalhes)

            containerLista.addView(card)
        }
    }
}