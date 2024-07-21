package com.example.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun proceedscreen(navController: NavController, items: MutableList<Vegitable>,item1 : MutableList<Healthystate>){
    Summary(items,item1)
}

@Composable
fun Summary(items: MutableList<Vegitable>,item1 : MutableList<Healthystate>){
    Column {
        Text(text = "Summary",
            fontSize = 25.sp,
            modifier = Modifier.padding(130.dp,10.dp))
        Text(text = "My Items",
            fontSize = 25.sp,
            modifier = Modifier.padding(40.dp,10.dp))
//        LazyColumn (content = {
//            items(items,item1) { item ->
//                CardItem(img = item.img, vegitable = item.itemname, healstate = item.healthname)
//            }
//        })
        LazyColumn {
            itemsIndexed(items) { index, item ->
                if (index < item1.size) {
                    CardItem(img = item.img, vegitable = item.itemname, healstate = item1[index].healthname)
                }
            }
        }
    }
}

@Composable
fun CardItem(img : Int, vegitable : String,healstate: String){
    Card (modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)){
        Row (Modifier.padding(4.dp)){
            Image(painter = painterResource(img), contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            Column (modifier = Modifier.padding(10.dp)){
                Text(text = "$vegitable",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(0.dp,10.dp,0.dp,20.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .height(1.dp)
                )
                Text(text = "$healstate",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(0.dp,10.dp,0.dp,20.dp))
            }

        }
    }
}

