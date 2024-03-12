package br.com.fiap.startupQair.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController) {
    Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFED145B))
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = { navController.navigate("air") },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Procurar", fontSize = 20.sp, color = Color.Blue)
        }
        Button(
            onClick = { navController.navigate("local") },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Localização atual", fontSize = 20.sp, color = Color.Blue)
        }
    }
}
