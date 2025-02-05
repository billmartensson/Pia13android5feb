package se.magictechnology.pia13android5feb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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

    var usererror by remember { mutableStateOf("") }

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
            usererror = ""

            if(calctype == "plus") {
                mathtotal = mathtotal + it
            }
            if(calctype == "minus") {
                mathtotal = mathtotal - it
            }
            if(calctype == "mult") {
                mathtotal = mathtotal * it
            }
            if(calctype == "div") {
                if(it == 0) {
                    usererror = "You cannot divide by zero"
                } else {
                    mathtotal = mathtotal / it
                }

            }
        }

        if(addnumber == null) {
            usererror = "You entered bad number"
        }

    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text("$mathtotal",
            fontSize = 100.sp,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        if(usererror != "") {
            Text(usererror,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(20.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Color.Red)
                    .padding(5.dp)
                    .clickable {
                        usererror = ""
                    }
            )
        }

        OutlinedTextField(
            value = usernumber,
            onValueChange = {
                /*
                if(it.toIntOrNull() != null || it == "") {
                    usernumber = it
                }
                 */
                usernumber = it
            },
            label = { Text("Enter a number") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)) {
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
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)) {
            Button(onClick = {
                docalc("mult")
            }) {
                Text("MULT")
            }

            Button(onClick = {
                docalc(calctype = "div")
            }) {
                Text("DIV")
            }
        }

        Column(modifier = Modifier.weight(1F)) {  }

        Button(modifier = Modifier.padding(bottom = 20.dp), onClick = {
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