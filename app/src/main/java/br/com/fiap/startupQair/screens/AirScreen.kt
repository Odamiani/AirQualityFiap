package br.com.fiap.startupQair.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.startupQair.R
import br.com.fiap.startupQair.model.ApiResponse
import br.com.fiap.startupQair.model.IndiceAr
import br.com.fiap.startupQair.model.IndiceArByLocal
import br.com.fiap.startupQair.service.RetrofitFactoryIndiceAr
import br.com.fiap.startupQair.ui.theme.Angkor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun AirScreen() {

    var wordState by remember { mutableStateOf("") }
    var listaIndiceArState by remember { mutableStateOf<ApiResponse?>(null) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column (modifier = Modifier.fillMaxWidth()) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Color(0xFF296BB4))

            ) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Image(
                    painter = painterResource(id = R.drawable.cidade),
                    contentDescription = "cidade",
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
                    fontFamily = Angkor


                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-55.dp)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "CONSULTA INDICE AR",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = Angkor
                        )
                        Text(
                            text = "Encontre o local que procura",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = Angkor
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        OutlinedTextField(
                            maxLines = 1,
                            value = wordState,
                            onValueChange = {
                                wordState = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(text = "Qual o lugar procurado?")
                            },
                            trailingIcon = {
                                IconButton(onClick = {
                                    val token = "715edb9e453f76499eb715194bc3f14fe36760e9"
                                    val call = RetrofitFactoryIndiceAr().getIndiceArService()
                                        .geIndiceArByWord(token, wordState)
                                    call.enqueue(object : Callback<ApiResponse> {
                                        override fun onResponse(
                                            call: Call<ApiResponse>,
                                            response: Response<ApiResponse>
                                        ) {
                                            listaIndiceArState = response.body()
                                        }

                                        override fun onFailure(
                                            call: Call<ApiResponse>,
                                            t: Throwable
                                        ) {
                                            Log.i("FIAP", "onResponse: ${t.message}", t)
                                        }
                                    })
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = ""
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier
                    .offset(y = (-40.dp))) {
                    items(listaIndiceArState?.data ?: emptyList()) { indice ->
                        CardIndiceAr(indice)
                    }
                }
            }
        }
    }
}

@Composable
fun CardIndiceAr(indice: IndiceAr) {

    // Função para calcular a cor com base no AQI
    fun calculateBackgroundColor(aqi: Int): Color {
        return when {
            aqi <= 50 -> Color.Green // Bom
            aqi <= 100 -> Color.Yellow // Moderado
            aqi <= 150 -> Color.Red // Não saudável para grupos sensíveis
            aqi <= 200 -> Color.Magenta // Não saudável
            aqi <= 300 -> Color.Red // Muito não saudável
            else -> Color.Black // Perigoso
        }
    }

    // Converter a String aqi para Int, assumindo que aqi seja uma String representando o índice de qualidade do ar
    val aqiValue = try {
        indice.aqi.toInt()
    } catch (e: NumberFormatException) {
        0 // Valor padrão se a conversão falhar
    }


    // Mensagem de qualidade do ar baseada no AQI
    val qualidadeAr = when {
        aqiValue <= 50 -> "A qualidade do ar está boa"
        aqiValue <= 100 -> "A qualidade do ar está moderada"
        aqiValue <= 150 -> "A qualidade do ar não é saudável para grupos sensíveis"
        aqiValue <= 200 -> "A qualidade do ar não é saudável"
        aqiValue <= 300 -> "A qualidade do ar é muito ruim"
        else -> "A qualidade do ar é perigosa"
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 4.dp)
            .border(border = BorderStroke(width = 2.dp, color = Color.Black))


    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFC7DFFA))
                    .fillMaxHeight()
                    .weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = indice.time.stime,
                    fontFamily = Angkor)
                Text(text = indice.station.name,
                    textAlign = TextAlign.Center,
                    fontFamily = Angkor)

                Text(text = qualidadeAr,
                    fontFamily = Angkor,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,

                    )
            }
            Column(
                modifier = Modifier
                    .background(Color(0xFFC7DFFA))
                    .fillMaxHeight()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(color = calculateBackgroundColor(aqiValue))
                        .size(80.dp)
                        .border(
                            border = BorderStroke(width = 2.dp, color = Color.Black)
                        )
                ) {
                    Text(
                        text = "${indice.aqi}",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 22.sp,
                        fontFamily = Angkor
                    )
                }
            }
        }
    }
}


@Composable
fun CardIndiceArByLocal(indice: IndiceArByLocal) {

    // Função para calcular a cor com base no AQI
    fun calculateBackgroundColor(aqi: Int): Color {
        return when {
            aqi <= 50 -> Color.Green // Bom
            aqi <= 100 -> Color.Yellow // Moderado
            aqi <= 150 -> Color.Red // Não saudável para grupos sensíveis
            aqi <= 200 -> Color.Magenta // Não saudável
            aqi <= 300 -> Color.Red // Muito não saudável
            else -> Color.Black // Perigoso
        }
    }

    // Converter a String aqi para Int, assumindo que aqi seja uma String representando o índice de qualidade do ar
    val aqiValue = try {
        indice.aqi.toInt()
    } catch (e: NumberFormatException) {
        0 // Valor padrão se a conversão falhar
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFC7DFFA))
                .fillMaxWidth()
                .padding(top = 40.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "${indice.city.name}",
                fontSize = 18.sp,
                fontFamily = Angkor,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            Box(
                modifier = Modifier
                    .background(color = calculateBackgroundColor(aqiValue))
                    .size(80.dp)
                    .border(
                        border = BorderStroke(width = 2.dp, color = Color.Black)
                    )


            ) {
                Text(
                    text = " ${indice.aqi}",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 22.sp,
                    fontFamily = Angkor


                )
            }

            Spacer(modifier = Modifier.padding(top = 50.dp))

            // Mensagem de qualidade do ar baseada no AQI
            val qualidadeAr = when {
                aqiValue <= 50 -> "A qualidade do ar está boa"
                aqiValue <= 100 -> "A qualidade do ar está moderada"
                aqiValue <= 150 -> "A qualidade do ar não é saudável para grupos sensíveis"
                aqiValue <= 200 -> "A qualidade do ar não é saudável"
                aqiValue <= 300 -> "A qualidade do ar é muito ruim"
                else -> "A qualidade do ar é perigosa"
            }
            Text(
                text = qualidadeAr,
                fontSize = 20.sp,
                fontFamily = Angkor,
                textAlign = TextAlign.Center
            )
        }
    }
}




