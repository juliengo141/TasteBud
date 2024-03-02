package com.example.tastebud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tastebud.MongoDBExample.insertDocument
import com.example.tastebud.compose.TasteBudApp
import com.example.tastebud.ui.theme.TasteBudTheme
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document


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
        insertDocument()
    }
}

object MongoDBConnection {
    val uri = "mongodb+srv://tastebuddev102:<TasteBud102>@cluster0.n4b5xe3.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"

    fun getMongoClient(): MongoClient {
        val connectionString = ConnectionString(uri)
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        return MongoClients.create(settings)
    }

}

object MongoDBExample {
    fun insertDocument() {
        val mongoClient = MongoDBConnection.getMongoClient()
        val database: MongoDatabase = mongoClient.getDatabase("RecipeTable")
        val collection: MongoCollection<Document> = database.getCollection("RecipeData")

        val document = Document("name", "John Doe")
            .append("email", "johndoe@example.com")
            .append("age", 30)

        collection.insertOne(document)

        mongoClient.close()
    }
}
