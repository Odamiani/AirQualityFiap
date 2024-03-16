package br.com.fiap.startupQair.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(modifier = Modifier.
                fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "CONSULTA INDICE AR",
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
                            val call = RetrofitFactoryIndiceAr().getIndiceArService().geIndiceArByWord(token, wordState)
                            call.enqueue(object : Callback<ApiResponse> {
                                override fun onResponse(
                                    call: Call<ApiResponse>,
                                    response: Response<ApiResponse>
                                ) {
                                    listaIndiceArState = response.body()
                                }

                                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
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
        LazyColumn() {
            items(listaIndiceArState?.data ?: emptyList()) { indice ->
                CardIndiceAr(indice)
            }
        }
    }
}

@Composable
fun CardIndiceAr(indice: IndiceAr) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(bottom = 4.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text(text = indice.time.stime)
                Text(text = indice.station.name)
            }
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = indice.aqi)
            }
        }
    }
}

@Composable
fun CardIndiceArByLocal(indice: IndiceArByLocal) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Text(text = "AQI: ${indice.aqi}")
            Text(text = "Station: ${indice.city.name}")
        }
    }
}