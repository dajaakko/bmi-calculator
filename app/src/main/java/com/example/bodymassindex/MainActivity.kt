package com.example.bodymassindex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bodymassindex.ui.theme.BodyMassIndexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BodyMassIndexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Bmi()
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BodyMassIndexTheme {
        Bmi()
    }
}
@Composable
fun Bmi() {

    var heightInput = remember { mutableStateOf("") }
    var weightInput = remember { mutableStateOf("") }
    val height = heightInput.value.toFloatOrNull() ?: 0.0f
    val weight = weightInput.value.toIntOrNull() ?: 0

    Log.d("BMI_Calculator", "Height: $height, Weight: $weight")

    val bmi = if (weight > 0 && height >0) weight / (height*height) else 0.0f
    Log.d("BMI_Calculator", "bmi: $bmi")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.title),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(
            value = heightInput.value,
            onValueChange = { newValue ->
                heightInput.value = newValue.replace(",", ".")
            },
            label = { Text(stringResource(R.string.height)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = weightInput.value,
            onValueChange = { newValue ->
                weightInput.value = newValue.replace(",", ".")
            },
            label = { Text(stringResource(R.string.weight)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(

            text = stringResource(R.string.result, String.format("%.2f", bmi))
        )
    }
}