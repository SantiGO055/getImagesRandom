package com.cursokotlin.imageslist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.bumptech.glide.load.engine.Resource
import com.cursokotlin.imageslist.Model.ImageClass
import com.cursokotlin.imageslist.Model.ImageRandom
import com.cursokotlin.imageslist.Model.urls
import com.cursokotlin.imageslist.ui.theme.ImagesListTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ImageClass()

        setContent {
            val viewModel: ImageClass by viewModels()

            var listaImagenes by remember{ mutableStateOf(listOf<ImageRandom>()) }
            val isLoading by viewModel.isLoading().observeAsState()

            ImagesListTheme {

                    BotonCallApi { item->
                        /**
                         * Aca va a obtener el imageslist de viewmodel con la lista modificada,
                         * cuando cambie actualiza en listaImagenes
                         */
                        listaImagenes = viewModel.imagesListMutable


                        println(viewModel.imagesListMutable)
                    }
                /**
                 * renderizo el listado de imagenes
                 */

                isLoading?.let { ComponentListImage(viewModel.imagesListMutable, it) }


            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    var imagesList = listOf(
        ImageRandom(
            "1",
            "imagen de prueba",
            "imagen de prueba",
            urls
        ),
        ImageRandom(
            "2",
            "imagen de prueba",
            "imagen de prueba",
            urls
        )
    )
    ComponentListImage(imagesList,true)
    BotonCallApi { item->
        println(item)

    }


}


@Composable
fun ComponentListImage(
    imagesList: List<ImageRandom>,
    isLoading: Boolean
//    vm: ImageClass
){


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(isLoading){
            CircularProgressIndicator()
        }
        else{
            LazyColumn() {
                items(imagesList) { img->
                    imageListItem(img)
                }
            }
        }

    }



}

@Composable
fun imageListItem(imageRandom : ImageRandom){
    val context = LocalContext.current


    Box(){

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    Toast
                        .makeText(context, "Hice click en ${imageRandom.urls.small}", Toast.LENGTH_SHORT)
                        .show()
                }
        ){
            Image(
                painter = rememberImagePainter(data = imageRandom.urls.small),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Column() {
                Text(text = imageRandom.alt_description ?: "Sin descripcion", style = MaterialTheme.typography.h6)

            }
        }
    }
}

@Composable
fun BotonCallApi(
    onListChange: (List<ImageRandom>) -> Unit
){
//    var listaImagenesTest by remember{ mutableStateOf(listOf<ImageRandom>()) }
    val viewModel: ImageClass = viewModel()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Box(){

        Scaffold(
    //        modifier = Modifier.zIndex(99F),
            floatingActionButton = {
                ExtendedFloatingActionButton(

                    text = { Text("Call api")},
                    onClick = {

                        scope.launch {
                            println("Aca obtengo la info")

                            viewModel.getRandomImageApi()
                            onListChange(viewModel.imagesListMutable)

                        }


                    },
                    elevation = FloatingActionButtonDefaults.elevation(15.dp)
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            bottomBar = {
                BottomAppBar(
                    // Defaults to null, that is, No cutout
                    cutoutShape = MaterialTheme.shapes.small.copy(
                        CornerSize(percent = 50)
                    )
                ) {
                    /* Bottom app bar content */
                }
            }


            ) {

        }
    }


}

