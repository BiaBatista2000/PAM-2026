package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.DebugButtonColors
import com.example.app.ui.theme.ErrorButtonColors
import com.example.app.ui.theme.InfoButtonColors
import com.example.app.ui.theme.WarningButtonColors

const val TAG = "App"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    var nome by remember { mutableStateOf("") }
    var filme by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = painterResource(R.drawable.logo)

            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(180.dp)
                    .height(120.dp)
            )

            Greeting("PAM 2")

            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Seu nome") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = filme,
                onValueChange = { filme = it },
                label = { Text("Nome do filme") },
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionButton(
                        text = "I",
                        buttonColors = ErrorButtonColors(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Log.e(TAG, "$nome avaliou o filme \"$filme\" como I")
                    }

                    ActionButton(
                        text = "R",
                        buttonColors = WarningButtonColors(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Log.w(TAG, "$nome avaliou o filme \"$filme\" como R")
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionButton(
                        text = "B",
                        buttonColors = DebugButtonColors(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Log.d(TAG, "$nome avaliou o filme \"$filme\" como B")
                    }

                    ActionButton(
                        text = "MB",
                        buttonColors = InfoButtonColors(),
                        modifier = Modifier.weight(1f)
                    ) {
                        Log.i(TAG, "$nome avaliou o filme \"$filme\" como MB")
                    }
                }
            }
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
    block: () -> Unit
) {
    ElevatedButton(
        onClick = block,
        shape = RoundedCornerShape(5.dp),
        colors = buttonColors,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "ATIVIDADE DE $name",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = MaterialTheme.colorScheme.secondary
    )
}

@Preview(showBackground = true, widthDp = 150, heightDp = 200)
@Composable
fun AppPreview() {
    AppTheme {
        App()
    }
}

@Preview(showBackground = true, widthDp = 120)
@Composable
fun ActionButtonPreview() {
    ActionButton(text = "Cadastrar") {
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("PAM 2")
    }
}