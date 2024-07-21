package com.example.homescreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import kotlinx.coroutines.delay
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException
import java.io.File
import androidx.compose.runtime.remember as remember

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
                    val navController= rememberNavController()
        AppContent(navController)
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
            proceedscreen(navController, items = itemList1,item1 = itemList2)
        }
        composable(route="circularbar"){
            circularbar(navController)
        }

    }
}

var proceed = "Proceed"

@Composable
fun AppContent(navController: NavController) {
    var showSplashScreen by remember { mutableStateOf(true) }

    LaunchedEffect(showSplashScreen) {
        delay(2000)
        showSplashScreen = false
    }

    Crossfade(targetState = showSplashScreen, label = "") { isSplashScreenVisible ->
        if (isSplashScreenVisible) {
            SplashScreen {
                showSplashScreen = false
            }
        } else {
            mainapp()
        }
    }
}


@Composable
fun SplashScreen(navigateToAuthOrMainScreen: () -> Unit) {
    // Navigate to AuthOrMainScreen after a delay
    LaunchedEffect(true) {
        // Simulate a delay of 2 seconds
        delay(2000)
        // Call the provided lambda to navigate to AuthOrMainScreen
        navigateToAuthOrMainScreen()
    }
    // Splash screen UI with transitions
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = TweenSpec(durationMillis = 500), label = ""
    )

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = painterResource(id = R.drawable.applogo),
            contentDescription = null,
            modifier = Modifier
                .size(650.dp)
                .scale(scale)
            // Apply the rotation effect
        )

    }
}
//navController: NavController
@Composable
fun Homescreen(navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            , colors = CardDefaults.cardColors(containerColor = Color(0xFFCAF0F8))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row {
                val context= LocalContext.current
                val intent= remember {
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://colab.research.google.com/drive/1VeVvGS6_tnzGb0ExI3Bp9QsPwJRqC2KH?usp=sharing"))
                }
                Text(
                    " Hi , User",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color(0xFF0096C7)
                    )
                )
                Button(onClick = {
context.startActivity(intent)
                },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier
                        .padding(110.dp,0.dp,0.dp,0.dp)
                ) {
                    Text(text = "Collab Link",
                        color = Color(0xFF3A3A40))
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
            Card (
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                , colors = CardDefaults.cardColors(containerColor = Color(0xFF0077B6))
            ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

             Text("")
             Text(
             "Add your information",
                  modifier = Modifier.clickable {
                        navController.navigate("profileuser")
                  },
                  textAlign = TextAlign.Center,
                  style = TextStyle(
                       color = Color.White,
                       fontSize = 30.sp,
                       fontStyle = FontStyle.Italic
                  )
                  )
                  Text("")
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
            Card(
                modifier = Modifier
                    .size(350.dp)
                    .padding(8.dp)
                    .fillMaxWidth()
                , colors = CardDefaults.cardColors(containerColor = Color(0xFF0077B6))
            ) {

                Text("")
                Text(
                    text = "          KITCHEN", style = TextStyle(
                        color = Color.White,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "           Add your all kitchen information",
                    modifier = Modifier,
                    style = TextStyle(
                        color = Color.White,
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
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .padding(16.dp)
                            .size(width = 200.dp, height = 50.dp), shape = RoundedCornerShape(60)
                    ) {
                        Text(text = "+Add item",
                            modifier = Modifier.clickable {
                                navController.navigate("addscreen")
                            }, color = Color(0xFF3A3A40))
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
                Button(onClick = {
                    navController.navigate("circularbar")
                },
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
                val flaskUrl = "https://75df-35-233-129-66.ngrok-free.app/process_json"
                sendJsonToFlask(jsonFile, flaskUrl)
                if(healthy == 1){
                    navController.navigate("circularbar")
                }
                else{
                    Toast.makeText(context, "PLease wait for 10 - 15 sec till AI is processing", Toast.LENGTH_SHORT).show()
                }
            },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(horizontal = 70.dp, vertical = 8.dp)
                    .fillMaxWidth()) {
                Text(text = "${proceed}")
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
                       Toast.makeText(context, "Added Item", Toast.LENGTH_SHORT).show()
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
                    modifier = Modifier.padding(vertical = 5.dp)){
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
                        Toast.makeText(context, "Added Item", Toast.LENGTH_SHORT).show()
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
data class Healthystate(val healthname: String)
data class Item(
    val img: Int,
    val itemName: String,
    val healthName: String
)

val itemList = mutableListOf<Jsonfile1>()
val itemList1 = mutableListOf<Vegitable>()
val itemList2 = mutableListOf<Healthystate>()
var healthstates = 99

fun getCategorylist(): MutableList<Category>{
    val list = mutableListOf<Category>()
    list.add(Category(img = R.drawable.img_1, img1 = R.drawable.img_2, itemname = "Eggs", itemname1 = "Hydrogenated oil"))
    list.add(Category(img = R.drawable.img_3, img1 = R.drawable.img_4, itemname = "Maida", itemname1 = "Toor Daal"))
    list.add(Category(img =R.drawable.img_5, img1 = R.drawable.img_6, itemname = "Peanut Butter", itemname1 = "Honey"))
    list.add(Category(img = R.drawable.img_7, img1 = R.drawable.img_8, itemname = "Tomato ketchup", itemname1 = "French Fries"))
    list.add(Category(img = R.drawable.img_9, img1 = R.drawable.img_10, itemname = "Coffee", itemname1 = "Potato Chips"))
    list.add(Category(img = R.drawable.img_12, img1 = R.drawable.img_11, itemname = "Vegetables", itemname1 = "Oats"))
    list.add(Category(img = R.drawable.img_14, img1 = R.drawable.img_13, itemname = "Sprouts", itemname1 = "Pasta"))
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

fun getHealthy():MutableList<Healthystate>{
    val list = mutableListOf<Healthystate>()
    return list
}

fun addHealthy(healthname: String): MutableList<Healthystate>{
    val file = Healthystate(healthname)
    itemList2.add(file)
    return itemList2
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomescreenTheme {
    //profileuser()
    }
}

//data class ResponseData(val status: String, val data: Map<String, Any>)
data class FoodItem(
    val amount: String,
    val food_name: String,
    val is_healthy: String
)

data class ResponseData(
    val data: List<FoodItem>
)

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
var healthcar=0f
var sizelist = 1
var totalsize=0
var healthysize=0
var healthy = 0

fun handleProcessedData(data: ResponseData) {

    // Use the processed data in your REST app
//    println("Processed Data: ${data.data}")
    println("Processed Data: ${data.data}")
    // Example of accessing the food items
    data.data.forEach { foodItem ->
        println("Food Name: ${foodItem.food_name}, Amount: ${foodItem.amount}, Is Healthy: ${foodItem.is_healthy}")
        totalsize++
        if(foodItem.is_healthy == "true" || foodItem.is_healthy == "yes" || foodItem.is_healthy == "True"|| foodItem.is_healthy == "Yes" || foodItem.is_healthy=="1" ){
            healthysize++
            addHealthy("Healthy")
        }
        else {
            addHealthy("Not Healthy")
        }
    }
   healthcar= healthysize*1f
    if(totalsize!=0){
        sizelist= totalsize
    }
    println("no of healthy item: ${healthcar}")
    println("no of total items: ${sizelist}")
    healthy = 1
    proceed = "Next"
}

