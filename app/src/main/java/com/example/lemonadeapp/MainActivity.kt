package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(){
    LemonadeImageButtonAndText(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.teal_200))
            .wrapContentSize(
                align = Alignment.Center
            )
    )
}

@Preview(showBackground=true)
@Composable
fun LemonadeImageButtonAndText(modifier: Modifier=Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier
    ){
        var stage by remember{ mutableIntStateOf(1) }
        var mustTap by remember{ mutableIntStateOf((2..4).random()) }
        val img = when(stage){
            1 -> R.drawable.lemonade_tree
            2 -> R.drawable.lemon
            3 -> R.drawable.lemonade
            else -> R.drawable.empty_cup
        }
        val theText = when(stage){
            1 -> R.string.select_lemon
            2 -> R.string.squeeze_lemon
            3 -> R.string.drink_lemon
            else -> R.string.restart
        }
        TextButton(onClick = {
            if (stage== 2){
                if(mustTap > 1) {
                    mustTap--
                }else stage++
            }else{
                if (stage >= 4) {
                    stage = 1
                    mustTap = (2..4).random()
                } else stage++
            }
        }){
            Image(
                painter = painterResource(img),
                contentDescription = stringResource(theText)
            )
        }
        Text(
            text=stringResource(theText)
        )
    }
}