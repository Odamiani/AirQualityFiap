package br.com.fiap.startupQair.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.startupQair.model.ApiResponseByLocal
import br.com.fiap.startupQair.service.RetrofitFactoryIndiceAr
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LocalScreen() {

    var cardState by remember { mutableStateOf<ApiResponseByLocal?>(null) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            {
                Button(onClick = {
                    val call = RetrofitFactoryIndiceAr().getIndiceArService().getIndiceArByLocal()
                    call.enqueue(object : Callback<ApiResponseByLocal> {
                        override fun onResponse(
                            call: Call<ApiResponseByLocal>,
                            response: Response<ApiResponseByLocal>
                        ) {
                            cardState = response.body()
                        }

                        override fun onFailure(call: Call<ApiResponseByLocal>, t: Throwable) {
                            Log.i("FIAP", "onResponse: ${t.message}", t)
                        }
                    })
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
                cardState?.let { CardIndiceArByLocal(it.data) }
            }
        }
    }
}