package com.example.firebasemedic.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composables.core.Icon
import com.example.firebasemedic.AuthState
import com.example.firebasemedic.AuthViewModel
import com.example.firebasemedic.icons.Health_and_safety
import com.example.firebasemedic.icons.Health_metrics
import com.example.firebasemedic.icons.Heart_plus
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(
//    navToAgendar: () -> Unit,
//    navToConsultar: () -> Unit,
    modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel, db : FirebaseFirestore
){
    val authState = authViewModel.authState.observeAsState()
    
   LaunchedEffect(authState.value) {
       when(authState.value){
           is AuthState.Unauthenticated -> navController.navigate("login")
           else -> Unit
       }
   }

    Column(
        Modifier
            .background(Color(0xFFEEF7F6))
            .fillMaxSize()
            .padding(16.dp),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(48.dp))


        Text("Firebase Medic",
            style = TextStyle(
                color = Color(0xFF5A5A5A),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Gerencie suas consultas de forma prática",
            style = TextStyle(
                color = Color(0xFF7D7D7D),
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("cadastrar") },
            modifier = Modifier.fillMaxWidth()
            ){
            Icon(
                imageVector = Heart_plus,
                contentDescription = "FirebaseMedic",
                tint = Color.White,
            )
            Text(" Agendar Consulta",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("read") },
            modifier = Modifier.fillMaxWidth()
            ){
            Icon(
                imageVector = Health_metrics,
                contentDescription = "FirebaseMedic",
                tint = Color.White,
            )
            Text(" Ver Agendamentos",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        
        TextButton(
            onClick = { authViewModel.signout() },
            ) {
            Text(
                text = "Encerrar Sessão",
                style = TextStyle(
                    color = Color(0xFF6AA6A6),
                    fontSize = 14.sp
                )
            )
        }
        Icon(
            imageVector = Health_and_safety,
            contentDescription = "FirebaseMedic",
            tint = Color(0xFF6AA6A6),
        )
    }
}