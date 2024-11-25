package com.example.firebasemedic.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebasemedic.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import com.composables.core.Icon
import com.example.firebasemedic.icons.Healing
import com.example.firebasemedic.icons.Health_and_safety
import com.example.firebasemedic.icons.Heart_minus
import com.example.firebasemedic.icons.Heart_plus

@Composable
fun ReadScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel, db : FirebaseFirestore){
    val user = Firebase.auth.currentUser
    val agendamentos = remember { mutableStateListOf<HashMap<String, String>>() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            while(isActive) {
                try {
                    db.collection("consultas")
                        .whereEqualTo("userid", (user?.uid ?: ""))
                        .get()
                        .addOnSuccessListener { documents ->
                                agendamentos.clear()
                                for(document in documents) {
                                    val consulta = hashMapOf(
                                        "documentId" to document.id,
                                        "nome" to "${document.data["nome"] ?: ""}",
                                        "datahorario" to "${document.data["datahorario"] ?: ""}",
                                        "espmedica" to "${document.data["espmedica"] ?: ""}",
                                    )
                                    //Log.w("ID", "Document ID ${document.id}")
                                    agendamentos.add(consulta)
                                }
                                Log.w("ReadScreen", "Dados atualizados com sucesso!")
                        }
                        .addOnFailureListener{ exception ->
                            Log.w("TesteRead", "Erro ao pegar documentos: ", exception)
                        }
                } catch (e: Exception) {
                    Log.e("ReadScreen", "Erro na operação periódica", e)
                }

                delay(10000L)
            }
        }
    }



    Column (
        Modifier
            .background(Color(0xFFEEF7F6))
            .fillMaxSize(),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Consultas Agendadas",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFDBA0DB)),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                agendamentos
            ) { consulta ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .shadow(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text("Paciente: ${consulta["nome"]}",
                                    style = TextStyle(
                                            fontSize = 16.sp, fontWeight = FontWeight.Bold
                                    )
                        )
                        Text("Data/Horário: ${consulta["datahorario"]}")
                        Text("Especialidade: ${consulta["espmedica"]}")
                    }
                    Column(Modifier.weight(1f),
                            horizontalAlignment = Alignment.End) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val docId = consulta["documentId"] ?: ""
                                navController.navigate("edit/$docId")
                            },
                            modifier = Modifier.padding(2.dp)
                        ) {
                            Icon(
                                imageVector = Healing,
                                contentDescription = "FirebaseMedic",
                                tint = Color.White,
                            )
                            Text(" Editar")
                        }
                        Spacer(modifier = Modifier.width(1.dp))
                        Button(
                            onClick = {
                                val docId = consulta["documentId"] ?: ""
                                if (docId.isNotEmpty()){
                                    cancelarConsulta(
                                        db = db,
                                        documentId = docId,
                                        onSucess = {
                                            agendamentos.remove(consulta)
                                        },
                                        onError = { exception ->
                                            Log.e("Erro", "Não foi possível cancelar a consulta", exception)
                                        }
                                    )
                                } else {
                                    Log.e("Erro", "documentId inválido ou vazio")
                                }
                            },
                            modifier = Modifier.padding(2.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF8A80)
                            )
                        ) {
                            Icon(
                                imageVector = Heart_minus,
                                contentDescription = "FirebaseMedic",
                                tint = Color.White,
                            )
                            Text(" Cancelar")
                        }
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = {
            navController.navigate("home")
        }) {
            Text("Retornar para Home",
                style = TextStyle(color = Color(0xFF6AA6A6),
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

fun cancelarConsulta(db: FirebaseFirestore, documentId: String, onSucess: () -> Unit, onError: (Exception) -> Unit){
    db.collection("consultas")
        .document(documentId)
        .delete()
        .addOnSuccessListener {
            Log.w("Delete", "Consulta cancelada com sucesso")
        }
        .addOnFailureListener { exception ->
            Log.w("Delete", "Erro ao cancelar consulta:", exception)
            onError(exception)
        }
}