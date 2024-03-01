package com.example.tastebud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tastebud.compose.TasteBudApp
import com.example.tastebud.ui.theme.TasteBudTheme
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //val database = getDatabase()

//        runBlocking{
//            database.listCollectionNames().collect{
//                //println(it)
//            }
//        }
        super.onCreate(savedInstanceState)
        setContent {
            TasteBudTheme {
                TasteBudApp()
            }
        }
    }
}

fun getDatabase(): MongoDatabase{
    val client = MongoClient.create(connectionString = "mongodb+srv://tastebuddev102:<TasteBud102>@cluster0.n4b5xe3.mongodb.net/?retryWrites=true&w=majority")
    return client.getDatabase(databaseName = "sample_restaurants")
}