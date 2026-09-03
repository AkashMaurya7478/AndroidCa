package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class CASETA : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           Payment()
        }
    }
}

@Composable
fun Payment() {
    var isProcessing by remember {mutableStateOf(false) }
    var status by remember {mutableStateOf("Status")}

    LaunchedEffect(isProcessing) {
        if (isProcessing) {
            status = "Payment Initiated"
            println("Payment is in progress")

            delay(3000)

            status = "Transaction Complete"
            println("Payment is Complete")

            isProcessing = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = status)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                isProcessing = true
            },
            enabled = !isProcessing
        ) {
            Text("Pay Now")
        }

        Spacer(modifier = Modifier.height(12.dp))


        if (isProcessing) {

            Button(
                onClick = {
                    isProcessing = false
                    status = "Payment Cancelled"
                }
            ) {
                Text("Cancel Payment")
            }

        }
        Spacer(modifier = Modifier.height(12.dp))
        if (isProcessing) {
            CircularProgressIndicator()
        }
    }
}