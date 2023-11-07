package com.hy.donelist.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hy.donelist.R
import com.hy.donelist.ui.ui.theme.DoneListTheme

@Composable
fun NumberScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.number_title),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.point_pink_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 60.dp)
        )

        Text(
            text = stringResource(R.string.number_help),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.help_grey_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 74.dp)
        )

        Button(
            //modifier = Modifier.weight(1f),
            // the button is enabled when the user makes a selection
            //enabled = selectedValue.isNotEmpty(),
            onClick = {},
            modifier = modifier
                .widthIn(min = 10.dp)
                .padding(bottom = 11.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContentColor = Color.LightGray,
                contentColor = colorResource(id = R.color.point_pink_color),
                disabledContainerColor = colorResource(id = R.color.point_pink_color)
            ),
            border = BorderStroke(2.dp, colorResource(id = R.color.point_pink_color))
        ) {
           // Text(stringResource(R.string.finish_number_setting))
        }

        Text(
            text = stringResource(R.string.count_number),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 11.dp)
        )

        Button(
            //modifier = Modifier.weight(1f),
            // the button is enabled when the user makes a selection
            //enabled = selectedValue.isNotEmpty(),
            onClick = {},
            modifier = modifier
                .widthIn(min = 10.dp)
                .padding(bottom = 74.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContentColor = Color.LightGray,
                contentColor = colorResource(id = R.color.point_pink_color),
                disabledContainerColor = colorResource(id = R.color.point_pink_color)
            ),
            border = BorderStroke(2.dp, colorResource(id = R.color.point_pink_color))
        ) {
            // Text(stringResource(R.string.finish_number_setting))
        }

        Button(
            //modifier = Modifier.weight(1f),
            // the button is enabled when the user makes a selection
            //enabled = selectedValue.isNotEmpty(),
            onClick = {},
            modifier = modifier
                .widthIn(min = 280.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContentColor = Color.LightGray,
                contentColor = colorResource(id = R.color.point_pink_color),
                disabledContainerColor = colorResource(id = R.color.point_pink_color)
            ),
            border = BorderStroke(2.dp, colorResource(id = R.color.point_pink_color))
        ) {
            Text(stringResource(R.string.finish_number_setting))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DoneListTheme {
        NumberScreen()
    }
}