package com.ifyezedev.cryptocurrencylibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ifyezedev.cryptocurrencylibrary.presentation.Screen
import com.ifyezedev.cryptocurrencylibrary.presentation.coin_detail.components.CoinDetailScreen
import com.ifyezedev.cryptocurrencylibrary.presentation.coin_list.components.CoinListScreen
import com.ifyezedev.cryptocurrencylibrary.presentation.ui.theme.CryptocurrencyLibraryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {

                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(
                                navController = navController
                            )
                        }

                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptocurrencyLibraryTheme {
        Greeting("Android")
    }
}