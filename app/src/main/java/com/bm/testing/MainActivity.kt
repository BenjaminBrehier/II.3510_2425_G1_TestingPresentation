package com.bm.testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bm.testing.ui.theme.TestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = input,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
        )
        Text(
            text = result,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
        )
        Column {
            // Lignes de boutons pour les chiffres
            for (i in 1..9 step 3) {
                Row {
                    for (j in 0..2) {
                        Button(
                            onClick = { input += (i + j).toString() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        ) {
                            Text(text = (i + j).toString())
                        }
                    }
                }
            }
            Row {
                Button(
                    onClick = { input += "0" },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(text = "0")
                }
                Button(
                    onClick = { result = "" + evaluateExpression(input.toString()) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(text = "=")
                }
            }

            // Boutons opération ligne
            Row {
                OperationButton(text = "+", onClick = { input += " + " })
                OperationButton(text = "-", onClick = { input += " - " })
                OperationButton(text = "*", onClick = { input += " * " })
                OperationButton(text = "/", onClick = { input += " / " })
                OperationButton(text = "Clear", onClick = {input = ""})
            }
        }
    }
}

fun evaluateExpression(expression: String): Double {
    val tokens = expression.split(" ").toMutableList()
    val numbers = mutableListOf<Double>()
    val operations = mutableListOf<Char>()

    for (token in tokens) {
        when {
            token.isDouble() -> numbers.add(token.toDouble())
            token[0] in setOf('+', '-', '*', '/') -> operations.add(token[0])
        }
    }

    // Évaluer la multiplication et la division d'abord
    val tempNumbers = mutableListOf<Double>()
    val tempOperations = mutableListOf<Char>()

    for (i in numbers.indices) {
        if (i == 0 || tempOperations.lastOrNull() !in setOf('*', '/')) {
            tempNumbers.add(numbers[i])
        } else {
            val lastOperation = tempOperations.removeAt(tempOperations.lastIndex)
            val lastNumber = tempNumbers.removeAt(tempNumbers.lastIndex)
            val result = when (lastOperation) {
                '*' -> lastNumber * numbers[i]
                '/' -> lastNumber / numbers[i]
                else -> numbers[i]
            }
            tempNumbers.add(result)
        }
        if (i < operations.size) {
            tempOperations.add(operations[i])
        }
    }

    // Évaluer l'addition et la soustraction
    var result = tempNumbers[0]
    for (i in tempOperations.indices) {
        result = when (tempOperations[i]) {
            '+' -> result + tempNumbers[i + 1]
            '-' -> result - tempNumbers[i + 1]
            else -> result
        }
    }

    return result
}

fun String.isDouble(): Boolean {
    return this.toDoubleOrNull() != null
}

@Composable
fun OperationButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    TestingTheme {
        CalculatorScreen()
    }
}
