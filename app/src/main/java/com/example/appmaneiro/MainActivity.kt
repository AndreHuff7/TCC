package com.example.appmaneiro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.background
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.draw.clip
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
            text = "Juju2D sem aura",
            fontSize = 40.sp,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
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
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuário") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            singleLine = true
        )

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            if (username.isBlank() || password.isBlank()) {
                errorMessage = "Por favor, preencha todos os campos."
            } else if (username == "admin" && password == "admin") {
                errorMessage = ""
                onProximoClick()
            } else {
                errorMessage = "Usuário ou senha incorretos."
            }
        }) {
            Text("Entrar")
        }
    }
}

@Composable
fun TerceiraTela() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val Red = Color(0xFF8A9A5B)
        val lightRed = Color(0xFF32CD32)

        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(Red)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Olá, ",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.size(28.dp),
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search bar
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(50))
                .background(Red)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "O que precisas?",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Destaques
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = lightRed)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "!!!!!DESTAQUES!!!!!",
                fontSize = 26.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Categories
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCircle("Cupons", lightRed)
            CategoryCircle("Ultimos\nServiços", lightRed)
            CategoryCircle("Favoritos", lightRed)
            CategoryCircle("Promoções", lightRed)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Services
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ServiceItem("Jardineiro", Icons.Default.Home, lightRed)
            ServiceItem("Encanador", Icons.Default.Build, lightRed)
            ServiceItem("Eletricista", Icons.Default.Warning, lightRed)
            ServiceItem("Barbeiro", Icons.Default.Face, lightRed)
        }
    }
}

@Composable
fun CategoryCircle(label: String, color: Color) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ServiceItem(name: String, icon: androidx.compose.ui.graphics.vector.ImageVector, bgColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(1.dp, Color.Gray)
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .border(1.dp, Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = name,
                modifier = Modifier.size(32.dp),
                tint = Color.Black
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .border(1.dp, Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}
