package br.com.fiap.startupQair.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.startupQair.R
import br.com.fiap.startupQair.model.ApiResponseByLocal
import br.com.fiap.startupQair.service.RetrofitFactoryIndiceAr
import br.com.fiap.startupQair.ui.theme.Angkor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@Composable
fun LocalScreen() {

    var cardState by remember { mutableStateOf<ApiResponseByLocal?>(null) }




    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Color(0xFF296BB4))

            ) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Image(
                    painter = painterResource(id = R.drawable.gps),
                    contentDescription = "GPS",
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
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-55.dp))
                    .fillMaxHeight(),

                    elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFC7DFFA))) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    )
                    {
                        Button(onClick = {
                            val call =
                                RetrofitFactoryIndiceAr().getIndiceArService().getIndiceArByLocal()
                            call.enqueue(object : Callback<ApiResponseByLocal> {
                                override fun onResponse(
                                    call: Call<ApiResponseByLocal>,
                                    response: Response<ApiResponseByLocal>
                                ) {
                                    cardState = response.body()
                                }

                                override fun onFailure(
                                    call: Call<ApiResponseByLocal>,
                                    t: Throwable
                                ) {
                                    Log.i("FIAP", "onResponse: ${t.message}", t)
                                }
                            })
                        },
                            modifier = Modifier.fillMaxWidth())

                        {
                            Text(text = "BUSCAR PELA MINHA LOCALIZAÇÃO ")
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar"
                            )

                        }

                        Spacer(modifier = Modifier.height(32.dp))


                        cardState?.let {CardIndiceArByLocal(it.data) }
                    }
                }
            }
        }
    }


}