package com.example.tastebud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)


@Composable
fun GreetingPreview() {
    TasteBudTheme {
        Greeting("Android")
    }
}
fun getDatabase(): MongoDatabase{
    val client = MongoClient.create(connectionString = "mongodb+srv://tastebuddev102:<TasteBud102>@cluster0.n4b5xe3.mongodb.net/?retryWrites=true&w=majority")
    return client.getDatabase(databaseName = "sample_restaurants")
}