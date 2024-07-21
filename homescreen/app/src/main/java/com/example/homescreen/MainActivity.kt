package com.example.homescreen

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homescreen.ui.theme.HomescreenTheme
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomescreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
       mainapp()
                }
            }
        }
    }
}
@Composable
fun profileuser(navController: NavController) {
    Column(
        modifier=Modifier.padding(22.dp)
    ){
        var name by remember {
            mutableStateOf("")
        }
        var age by remember {
            mutableStateOf("")
        }
        var weight by remember {
            mutableStateOf("")
        }
        var height by remember {
            mutableStateOf("")
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(80.dp))
            Row {
                Text("                       ")
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier.size(166.dp)
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Name",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                )
            )
                OutlinedTextField(value = name, onValueChange = {
                    name = it
                }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Age",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                )
            )
                OutlinedTextField(value = age, onValueChange = {
                    age = it
                },modifier=Modifier.fillMaxWidth())
            
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Weight",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                )
            )
            OutlinedTextField(value = age, onValueChange = {
                weight= it
            },modifier=Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Height",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                )
            )
            OutlinedTextField(value = age, onValueChange = {
                height = it
            },modifier=Modifier.fillMaxWidth())


        }
    }
}
@Composable
fun mainapp(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "homescreen" ){
        composable(route = "homescreen"){
             Homescreen(navController)
        }
        composable(route="profileuser"){
             profileuser(navController)
        }
        composable(route = "addscreen"){
            AddScreen(navController)
        }
        composable(route="proceedscreen"){
            proceedscreen(navController, items = itemList1)
        }

    }
}

@Composable
fun Homescreen(navController: NavController) {
    Card(
        modifier = Modifier.padding(8.dp)
            , colors = CardDefaults.cardColors(containerColor = Color.Gray)


    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                " Hi ,name",
              //  modifier = Modifier.size(1.dp),
                style = TextStyle(
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(70.dp))
            Card (
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                , colors = CardDefaults.cardColors(containerColor = Color.White)

            ){
                Text("")
                Text(
                    "          Add your\n " +
                            "       information",
                    modifier = Modifier.clickable {
                        navController.navigate("profileuser")
                    },
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 38.sp,
                        fontStyle = FontStyle.Italic
                    )
                )
                Text("")
            }
            Spacer(modifier = Modifier.height(120.dp))
            Card(
                modifier = Modifier
                    .size(978.dp)
                    .padding(8.dp)
                    .fillMaxWidth()
                , colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                Text("")
                Text(
                    text = "          KITCHEN", style = TextStyle(
                        color = Color.Black,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "              Add your all kitchen information",
                    modifier = Modifier,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic
                    )
                )
                Spacer(modifier = Modifier.height(36.dp))
                Row {
                    Text("             ")
                    Button(
                        onClick = {


                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .size(width = 200.dp, height = 50.dp), shape = RoundedCornerShape(60)
                    ) {
                        Text(text = "+Add item",
                            modifier = Modifier.clickable {
                                navController.navigate("addscreen")
                            }, color = Color.White)
                    }
                }

            }
        }
    }
}
@Composable
fun AddScreen(navController: NavController){
    var state = remember{ mutableStateOf("") }
    var context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Your Kitchen",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal =110.dp, vertical = 16.dp)
            )
            Row (modifier = Modifier.padding(vertical = 16.dp)){
                TextField(value = state.value,
                    onValueChange = {state.value = it},
                    label = { Text(text = "Enter Message") } )
                Button(onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(100.dp, 55.dp)
                ){
                    Text(text = "Search")
                }
            }
            Card (modifier = Modifier
                .height(500.dp)
                .padding(10.dp)){
                LazyColumn (content = {
                    items(getCategorylist()){ item ->
                        Cardfun(img = item.img,img1 = item.img1, itemname = item.itemname, itemname1 = item.itemname1)
                    }
                })
            }
            Button(onClick = {
                val items = itemList

                val gson = Gson()
                val jsonString = gson.toJson(items)

                val filePath = File(context.filesDir, "items.json")
                filePath.writeText(jsonString)

                val jsonString1 = filePath.readText()
                Log.d("CheckJsonFile", "Contents of items.json: $jsonString1")

                val jsonFile = filePath
                val flaskUrl = "https://bf2c-34-16-188-15.ngrok-free.app/process_json"
                sendJsonToFlask(jsonFile, flaskUrl)

                navController.navigate("proceedscreen")

            },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(horizontal = 70.dp, vertical = 8.dp)
                    .fillMaxWidth()) {
                Text(text = "Proceed")
            }
        }
    }
}

@Composable
fun Cardfun(img: Int,img1: Int,itemname:String,itemname1: String) {
    var state2= remember{ mutableIntStateOf(0) }
    var state1 = remember{ mutableIntStateOf(0) }
    var context = LocalContext.current
    Row {
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .height(275.dp)
            ,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(img),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 5.dp)){
                    Icon(imageVector = Icons.Filled.Minimize, contentDescription = "",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 10.dp, 15.dp)
                            .clickable {
                                if (state1.value > 0) {
                                    state1.value = state1.value - 50
                                }
                            }
                    )
                    Text(
                        text = "${state1.value}",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp)
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .clickable { state1.value = state1.value + 50 })
                }
                Text("$itemname",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                )
                Button(
                    onClick = {
                       addJson1(itemname, state1.value.toString() + " serving")
                       addVegitable(itemname, img)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(text = "Add")
                }
            }
        }
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .height(275.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(img1),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 10.dp)){
                    Icon(imageVector = Icons.Filled.Minimize, contentDescription = "",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 10.dp, 15.dp)
                            .clickable {
                                if (state2.value > 0) {
                                    state2.value = state2.value - 50
                                }
                            }
                    )
                    Text(
                        text = "${state2.value}",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp)
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .clickable { state2.value = state2.value + 50 })
                }
                Text("$itemname1",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                )
                Button(
                    onClick = {
                        addJson1(itemname1, state2.value.toString() + " serving")
                        addVegitable(itemname1, img1)
                        Toast.makeText(context, "Sahil Kumar", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(text = "Add")
                }
            }
        }
    }

}

data class Category(val img: Int, val img1: Int, val itemname: String, val itemname1: String)
data class Jsonfile1(val amount : String, val food_name: String)
data class Vegitable(val itemname: String,val img: Int)
val itemList = mutableListOf<Jsonfile1>()
val itemList1 = mutableListOf<Vegitable>()

fun getCategorylist(): MutableList<Category>{
    val list = mutableListOf<Category>()
    list.add(Category(img = R.drawable.img, img1 = R.drawable.img, itemname = "Akshit", itemname1 = "Akshit"))
    list.add(Category(img = R.drawable.img, img1 = R.drawable.img, itemname = "Tomato", itemname1 = "Akshit"))
    list.add(Category(img =R.drawable.img, img1 = R.drawable.img, itemname = "Sandeep", itemname1 = "Akshit"))
    list.add(Category(img = R.drawable.img, img1 = R.drawable.img, itemname = "Sourav", itemname1 = "Akshit"))
    list.add(Category(img = R.drawable.img, img1 = R.drawable.img, itemname = "Tsads", itemname1 = "Akshit"))
    list.add(Category(img = R.drawable.img, img1 = R.drawable.img, itemname = "Tomatosda", itemname1 = "Akshit"))
    list.add(Category(img = R.drawable.img, img1 = R.drawable.img, itemname = "Todasdfto", itemname1 = "Akshit"))
    return list
}
fun getJson1(): MutableList<Jsonfile1>{
    val list = mutableListOf<Jsonfile1>()
    return list
}
fun addJson1( itemname : String,itemsize: String): MutableList<Jsonfile1>{
    val file = Jsonfile1(itemsize,itemname)
    itemList.add(file)
    return itemList
}

fun getVegitable():MutableList<Vegitable>{
    val list = mutableListOf<Vegitable>()
    return list
}

fun addVegitable(itemname: String, img: Int): MutableList<Vegitable>{
    val file = Vegitable(itemname, img )
    itemList1.add(file)
    return itemList1
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomescreenTheme {
    //profileuser()
    }
}

data class ResponseData(val status: String, val data: Map<String, Any>)

fun sendJsonToFlask(jsonFile: File, flaskUrl: String) {
    val client = OkHttpClient()
    val gson = Gson()

    // Read the JSON file
    val jsonData = jsonFile.readText()

    // Create the request body with JSON data
    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = RequestBody.create(mediaType, jsonData)

    // Build the request
    val request = Request.Builder()
        .url(flaskUrl)
        .post(requestBody)
        .build()

    // Send the request and handle the response
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                // Parse the response JSON
//                val responseData = gson.fromJson(response.body?.charStream(), ResponseData::class.java)
//                println("Response Data: $responseData")
//                // Handle the processed data
//                handleProcessedData(responseData)
                val rawJson = response.body?.string()
                println("Raw JSON Response: $rawJson")

                // Now parse the JSON response
                try {
                    val responseData = gson.fromJson(rawJson, ResponseData::class.java)
                    println("Parsed Response Data: $responseData")
                    // Handle the processed data
                    handleProcessedData(responseData)
                } catch (e: JsonSyntaxException) {
                    e.printStackTrace()
                    println("Failed to parse JSON: $rawJson")
                }
            }
        }
    })
}

fun handleProcessedData(data: ResponseData) {
    // Use the processed data in your REST app
    println("Processed Data: ${data.data}")
}