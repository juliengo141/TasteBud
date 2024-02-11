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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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

/*
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.mongodb.kotlin.client.coroutine.MongoClient

fun main(args: Array<String>){
    println("Hello")
    println("Program arguments: ${args.joingToString()}")

    val database = getDatabase()

    runBlocking{
        database.listCollectionNames().collect{
            println(it)
        }
    }



}

fun getDatabase(): MongoDatabase{
    val
    val client = MongoClient.create(connectionString = "")
    return client.getDatabase(database = "sample_restaurants")
}



 */