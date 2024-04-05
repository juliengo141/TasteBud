 package com.example.tastebud.screens.home

 import NavBarScaffold

 import androidx.compose.foundation.layout.*
 import androidx.compose.material3.*
 import androidx.compose.runtime.*
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.unit.dp
 import androidx.navigation.NavController
 import com.example.tastebud.screens.SharedViewModel
 import androidx.compose.foundation.Image
 import androidx.compose.foundation.background
 import androidx.compose.foundation.border
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.shape.CircleShape
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.AccountCircle
 import androidx.compose.material.icons.filled.CheckBox
 import androidx.compose.material.icons.filled.Timer
 import androidx.compose.material3.MaterialTheme.typography
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.draw.clip

 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.graphics.vector.ImageVector
 import androidx.compose.ui.layout.ContentScale
 import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.res.vectorResource
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
 import com.example.tastebud.R
 import com.example.tastebud.ui.theme.Inter
 import com.example.tastebud.ui.theme.TasteBudAccent
 import com.example.tastebud.ui.theme.TasteBudBackground
 import com.example.tastebud.ui.theme.TasteBudGreen
 import com.example.tastebud.ui.theme.TasteBudOrange
 import com.example.tastebud.ui.theme.TasteBudRed


 @Composable
 fun ProfileScreen(navController: NavController, sharedViewModel: SharedViewModel) {
     NavBarScaffold(navController, "Profile") { ProfileContent(navController, it) }
 }

 @Composable
 fun ProfileContent(navController: NavController, innerPadding: PaddingValues) {
     Column(
         modifier = Modifier.padding(innerPadding).padding(0.dp, 20.dp),
         verticalArrangement = Arrangement.spacedBy(28.dp),
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         // Profile Header
         ProfileHeader()

         // Icons Row
         IconsRow()

         // Cards Section
         CardsSection(navController)
     }
 }

 @Composable
 fun ProfileHeader() {
     Row(
         modifier = Modifier.fillMaxWidth().padding(10.dp),
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.spacedBy(8.dp)
     ) {
         // Profile Icon
         Icon(
             imageVector = Icons.Filled.AccountCircle,
             contentDescription = "Localized description",
             modifier = Modifier.size(50.dp)
         )

         // User's Name and Email (Random for now)
         Column {
             Text("John Doe", style = typography.bodyLarge, fontWeight = FontWeight.Bold)
             Text("john.doe@example.com", style = typography.bodyLarge, fontWeight = FontWeight.Medium)
         }
     }
 }

 @Composable
 fun IconsRow() {
     Column(
         modifier = Modifier.fillMaxWidth().padding(40.dp, 0.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.spacedBy(16.dp)
     ) {
         Text(text = "Dietary Restrictions", fontWeight = FontWeight.Bold)
         Row(
             modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.spacedBy(16.dp),
         ) {
             // First row of circles
             CircleIcon(R.drawable.dairyfree, "Dairy Free")
             CircleIcon(R.drawable.vegan, "Vegan")
             CircleIcon(R.drawable.gluten, "Gluten Free")
         }
         Row(
             modifier = Modifier.fillMaxWidth(),
             horizontalArrangement = Arrangement.spacedBy(16.dp),
         ) {
             // Second row of circles
             CircleIcon(R.drawable.vegatarian, "Vegetarian")
             CircleIcon(R.drawable.pescatarian, "Pescatarian")
             CircleIcon(R.drawable.keto, "Keto")
         }
     }
 }

 @Composable
 fun CircleIcon(icon: Int, diet: String) {
     Column(
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         Box(
             modifier = Modifier
                 .size(100.dp)
                 .clip(CircleShape)
                 .background(TasteBudAccent),
             contentAlignment = Alignment.Center
         ) {
             Image(
                 painter = painterResource(id = icon),
                 contentDescription = "Circle Icon",
                 modifier = Modifier.size(75.dp)
             )
         }
         Spacer(modifier = Modifier.height(4.dp))
         Text(
             text = diet,
             style = typography.bodyMedium,
             color = Color.Black, fontWeight = FontWeight.Bold
         )
     }
 }

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun CardsSection(navController: NavController) {
     Row(
         modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceAround
     ) {
         // First Card
         Card(
             modifier = Modifier.fillMaxWidth(0.5f).padding(12.dp),
             //elevation = 8.dp,
             //backgroundColor = Color.White,
             shape = RoundedCornerShape(16.dp)
         ) {
             Column(
                 modifier = Modifier.padding(16.dp)
             ) {
                 Text(
                     text = "Recipes Completed",
                     style = typography.headlineMedium,
                     fontWeight = FontWeight.Bold,
                     modifier = Modifier.padding(bottom = 8.dp)
                 )
                 Row(verticalAlignment = Alignment.CenterVertically){
                     Icon(
                         imageVector = Icons.Filled.CheckBox,
                         contentDescription = "Localized description",
                         modifier = Modifier.size(40.dp)
                     )
                     Text(
                         text = "145 ",
                         style = typography.bodyMedium
                     )
                     Text(
                         text = "Completed",
                         style = typography.bodyMedium
                     )

                 }
             }
         }

         // Second Card
         Card(
             modifier = Modifier.fillMaxWidth(1f).padding(12.dp),
//             elevation = 8.dp,
//             backgroundColor = Color.White,
             shape = RoundedCornerShape(16.dp)
         ) {
             Column(
                 modifier = Modifier.padding(16.dp)
             ) {
                 Text(
                     text = "Cooking Minutes",
                     style = typography.headlineMedium,
                     fontWeight = FontWeight.Bold,
                     modifier = Modifier.padding(bottom = 8.dp)
                 )

                 Row(verticalAlignment = Alignment.CenterVertically){
                     Icon(
                     imageVector = Icons.Filled.Timer,
                     contentDescription = "Localized description",
                     modifier = Modifier.size(40.dp)
                 )
                     Text(
                         text = "123456 ",
                         style = typography.bodyMedium
                     )
                     Text(
                         text = "Minutes",
                         style = typography.bodyMedium
                     )

                 }

             }
         }

     }
 }