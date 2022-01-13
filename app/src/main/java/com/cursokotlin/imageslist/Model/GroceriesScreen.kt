package com.cursokotlin.imageslist.Model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cursokotlin.imageslist.R


@Preview(showSystemUi = true)
@Composable
fun GroceriesScreen(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        var groceriesState by remember{ mutableStateOf(listOf("milk","water")) }

        GroceryInput { item->
            //uupdate groceries state with the added item
            //escucho un cambio y se lo doy a groceriesState
            groceriesState = groceriesState + listOf(item)
        }

        GroceryList(groceriesState)
    }
}

@Composable
fun GroceryInput(
    onGroceryItemAdded: (String) -> Unit
){
    var textState by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = { textState = it },
        label = { Text(text = stringResource(R.string.new_item))}
        )

    Column(
        Modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onGroceryItemAdded(textState)
                textState = ""
            }
        ) {
            Text("Add new item")
        }

    }
}
@Composable
fun GroceryList(groceries: List<String>){

    //declaro la lista

    LazyColumn(){

        items(groceries.size){ index->
            Text(text = groceries[index])
        }
    }
}
