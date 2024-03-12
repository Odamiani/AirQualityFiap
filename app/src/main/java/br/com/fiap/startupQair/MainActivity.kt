package br.com.fiap.startupQair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.startupQair.screens.AirScreen
import br.com.fiap.startupQair.screens.FirstScreen
import br.com.fiap.startupQair.screens.LocalScreen
import br.com.fiap.startupQair.ui.theme.AirQualityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirQualityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "first"
                    ){
                        composable(route = "first"){ FirstScreen(navController) }
                        composable(route = "air"){ AirScreen() }
                        composable(route = "local"){ LocalScreen() }
                    }
                }
            }
        }
    }
}

