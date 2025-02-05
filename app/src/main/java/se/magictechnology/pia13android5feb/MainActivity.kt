package se.magictechnology.pia13android5feb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.magictechnology.pia13android5feb.ui.theme.Pia13android5febTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pia13android5febTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Mathscreen()
                }
            }
        }
    }
}

@Composable
fun Mathscreen() {

    var mathtotal by remember { mutableStateOf(0) }
    var usernumber by remember { mutableStateOf("") }

    fun docalc(calctype : String) {

        var addnumber = usernumber.toIntOrNull()

        /* SWIFT
        if let siffra = addnumber {
            mathtotal = mathtotal + siffra
        }
         */

        /*
        if(addnumber != null) {
            mathtotal = mathtotal + addnumber
        }
        */

        if(addnumber == null) {
            val numberWords = arrayOf(
                "zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten"
            )

            val index = numberWords.indexOf(usernumber.lowercase())

            if(index != -1) {
                addnumber = index
            }
        }

        addnumber?.let {
            if(calctype == "plus") {
                mathtotal = mathtotal + it
            }
            if(calctype == "minus") {
                mathtotal = mathtotal - it
            }

        }

    }





    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("$mathtotal", fontSize = 70.sp)

        TextField(
            value = usernumber,
            onValueChange = {
                if(it.toIntOrNull() != null || it == "") {
                    usernumber = it
                }
            },
            label = { Text("Enter a number") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )

        Button(onClick = {
            docalc("plus")
        }) {
            Text("PLUS")
        }

        Button(onClick = {
            docalc(calctype = "minus")
        }) {
            Text("MINUS")
        }

        Button(onClick = {
            mathtotal = 0
        }) {
            Text("RESET")
        }


    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pia13android5febTheme {
        Mathscreen()
    }
}