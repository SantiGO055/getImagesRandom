package com.cursokotlin.imageslist.Model

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.imageslist.Service.APIService
import com.cursokotlin.imageslist.Util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.github.cdimascio.dotenv.dotenv
import kotlin.reflect.KProperty

var urls: Urls = Urls("https://images.unsplash.com/photo-1640984342197-ef90c8ee2974?ixid=MnwyODExMTh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2NDE5MDgwNzU&ixlib=rb-1.2.1",
    "https://images.unsplash.com/photo-1640984342197-ef90c8ee2974?crop=entropy&cs=srgb&fm=jpg&ixid=MnwyODExMTh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2NDE5MDgwNzU&ixlib=rb-1.2.1&q=85",
    "https://images.unsplash.com/photo-1640984342197-ef90c8ee2974?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyODExMTh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2NDE5MDgwNzU&ixlib=rb-1.2.1&q=80&w=1080",
    "https://images.unsplash.com/photo-1640984342197-ef90c8ee2974?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyODExMTh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2NDE5MDgwNzU&ixlib=rb-1.2.1&q=80&w=400",
    "https://images.unsplash.com/photo-1640984342197-ef90c8ee2974?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyODExMTh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2NDE5MDgwNzU&ixlib=rb-1.2.1&q=80&w=200")

class ImageClass (): ViewModel() {

//    private var asd = mutableStateListOf<ImageRandom>()
//    val listaAsd: List<ImageRandom>
//        get() = asd



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
    var imagesListMutable by mutableStateOf(imagesList)

    private val isLoading = MutableLiveData(false)
    fun isLoading(): LiveData<Boolean> = isLoading




    fun getRandomImageApi() {

        isLoading.postValue(true)

        viewModelScope.launch() {
            val env = System.getenv("config")

            println("env")
            println(env)
            val call = getRetrofit().create(APIService::class.java)
                .getRandomImage("random?client_id=okTUdfxNZJjAmiu8sirizJSXu9_1hfOiEtNyaAbyqGk&count=${Constants.count}")
            val imageAux = call.body()
            if (call.isSuccessful) {

                if (imageAux != null) {

                    imagesList = imageAux

                    imagesListMutable = imageAux

                    println("en la llamada a la api")

                    println(imagesListMutable)

                    isLoading.postValue(false)
                    println(isLoading)



                }

            }


        }


    }
    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/photos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

