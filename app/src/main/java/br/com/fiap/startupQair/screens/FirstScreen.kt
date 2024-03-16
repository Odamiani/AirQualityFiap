package br.com.fiap.startupQair.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.startupQair.R
import br.com.fiap.startupQair.ui.theme.Angkor


@Composable
fun FirstScreen(navController: NavController) {

    Box (modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Color(0xFF296BB4))

            ) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Image(
                    painter = painterResource(id = R.drawable.logoqair),
                    contentDescription = "lOGO",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = "Q Air",
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,

                    )

            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(30.dp),
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-55.dp))
                        .height(250.dp),

                    elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA))
                ) {

                    Text(text = "Qual o local que deseja pesquisar ?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = Angkor
                        )




                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = { navController.navigate("air") },
                        colors = ButtonDefaults.buttonColors(Color(0xFF296BB4)),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Procurar", fontSize = 20.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { navController.navigate("local") },
                        colors = ButtonDefaults.buttonColors(Color(0xFF296BB4)),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()

                    ) {
                        Text(text = "Localização atual", fontSize = 20.sp, color = Color.White)
                    }

                }
                Text(
                    text = "2024 © Todos os direitos reservados.",
                    fontSize = 15.sp,
                )

            }
        }
    }
}
