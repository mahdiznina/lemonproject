package com.example.diceroller1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller1.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> LemonTextAndImage(
                textLabelResource = R.string.lemon_select,
                drawableResource = R.drawable.lemon_tree,
                contentDescriptionResource = R.string.lemon_tree_content_description,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
            2 -> LemonTextAndImage(
                textLabelResource = R.string.lemon_squeeze,
                drawableResource = R.drawable.lemon_squeeze,
                contentDescriptionResource = R.string.lemon_content_description,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
            3 -> LemonTextAndImage(
                textLabelResource = R.string.lemon_drink,
                drawableResource = R.drawable.lemon_drink,
                contentDescriptionResource = R.string.lemon_drink_content_description,
                onImageClick = { currentStep = 4 }
            )
            4 -> LemonTextAndImage(
                textLabelResource = R.string.lemon_empty_glass,
                drawableResource = R.drawable.lemon_tree,
                contentDescriptionResource = R.string.lemon_restart_content_description,
                onImageClick = { currentStep = 1 }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabelResource: Int,
    drawableResource: Int,
    contentDescriptionResource: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textLabelResource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = drawableResource),
            contentDescription = stringResource(id = contentDescriptionResource),
            modifier = Modifier
                .border(2.dp, Color(105, 205, 216), shape = RoundedCornerShape(4.dp))
                .clickable(onClick = onImageClick)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}
