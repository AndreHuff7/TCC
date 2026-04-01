package com.example.appmaneiro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 1. Estado da tela
            var telaAtual by remember { mutableStateOf("loading") }

            // 2. Timer apenas para sair do Loading
            LaunchedEffect(Unit) {
                delay(3000)
                telaAtual = "segunda"
                // Removi o segundo delay para o botão ser o único a levar à terceira tela
            }

            // 3. O 'when' controla TUDO. Não precisa de 'if' embaixo.
            when (telaAtual) {
                "loading" -> {
                    TelaLoading()
                }
                "segunda" -> {
                    SegundaTela(onProximoClick = { telaAtual = "terceira" })
                }
                "terceira" -> {
                    TerceiraTela()
                }
            }
        }
    }
}

// --- FUNÇÕES DAS TELAS ---

@Composable
fun TelaLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Leo sem aura",
            fontSize = 60.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.gato),
            contentDescription = "Gato",
            modifier = Modifier.size(200.dp),
        )
    }
}

@Composable
fun SegundaTela(onProximoClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "O Leo ainda\nnão tem aura",
            fontSize = 40.sp,
            color = Color.Blue,
            lineHeight = 45.sp,
            fontWeight = FontWeight.Thin,
            letterSpacing = 4.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onProximoClick) {
            Text("Desejas Farmar aura?")
        }
    }
}

@Composable
fun TerceiraTela() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Nivel Máximo de aura atingido (estou cego)",
            fontSize = 40.sp,
            color = Color.Cyan,
            fontWeight = FontWeight.Bold,
            lineHeight = 45.sp,
            letterSpacing = 4.sp
        )
    }
}