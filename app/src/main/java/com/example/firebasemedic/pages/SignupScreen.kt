package com.example.firebasemedic.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebasemedic.AuthState
import com.example.firebasemedic.AuthViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SignupScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel, db : FirebaseFirestore){
    var usermail by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message,Toast.LENGTH_SHORT).show()
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

        Text(
            "Bem-vindo ao FirebaseMedic!",
            style = TextStyle(
                color = Color(0xFF5A5A5A),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Cadastre-se para continuar",
            style = TextStyle(
                color = Color(0xFF7D7D7D),
                fontSize = 16.sp,
            )
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text("E-mail",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFF5A5A5A)
            ))
        TextField(
            value = usermail,
            onValueChange = { usermail = it },
            modifier = Modifier
                .background(Color.White)
                .padding(vertical = 8.dp),
            textStyle = TextStyle(color = Color(0xFF333333), fontSize = 14.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Senha",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color(0xFF5A5A5A)
            ))
        TextField(value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .background(Color.White)
                .padding(vertical = 8.dp),
            textStyle = TextStyle(color = Color(0xFF333333), fontSize = 14.sp),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            authViewModel.signup(usermail,password)
        },
            enabled = authState.value != AuthState.Loading,
            modifier = Modifier.fillMaxWidth()
            ){
            Text("Cadastrar Conta",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate("login")
        }){
            Text("Já tem uma conta? Faça Login",
                style = TextStyle(
                    color = Color(0xFF6AA6A6),
                    fontSize = 14.sp
                )
            )
        }
    }
}