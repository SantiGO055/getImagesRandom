package com.cursokotlin.imageslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cursokotlin.imageslist.Model.ImageRandom

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){

        }
    }

}

@Composable
fun MainScreen(navController: NavController){
    Box(contentAlignment = Alignment.Center,

        ){

    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 50.dp)) {

    }
}
@Composable
fun DetailScreen(listaImagenes: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()

        ) {
        Text(text = "Hello, ${listaImagenes}")
    }
}