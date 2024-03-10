package br.com.fiap.startupQair

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.startupQair.model.ApiResponse
import br.com.fiap.startupQair.model.IndiceAr
import br.com.fiap.startupQair.service.RetrofitFactoryIndiceAr
import br.com.fiap.startupQair.ui.theme.AirQualityTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirQualityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AirScreen()
                }
            }
        }
    }
}

@Composable
fun AirScreen() {

    var wordState by remember { mutableStateOf("") }
    var listaIndiceArState by remember { mutableStateOf<ApiResponse?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "CONSULTA INDICE AR", fontSize = 24.sp)
                Text(
                    text = "Encontre o local que procura",
                    fontSize = 20.sp
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
        .padding(bottom = 4.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Text(text = "AQI: ${indice.aqi}")
            Text(text = "Time: ${indice.time.stime}")
            Text(text = "Station: ${indice.station.name}")
        }
    }
}