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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hy.donelist.R

@Composable
fun LoginScreenDisplay(
    modifier: Modifier = Modifier,
    onLoginButtonClicked: (Int) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.screen_title),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.point_pink_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            shape = RoundedCornerShape(25.dp),
            label = {
                Text(
                    text = stringResource(R.string.login_screen_id_title),
                    color = colorResource(id = R.color.point_pink_color),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            modifier = modifier
                .padding(bottom = 20.dp)
                .widthIn(min = 280.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = colorResource(id = R.color.point_pink_color),
                focusedBorderColor = colorResource(id = R.color.point_pink_color),
                unfocusedLeadingIconColor = colorResource(id = R.color.point_pink_color)
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(25.dp),
            label = {
                Text(
                    text = stringResource(R.string.login_screen_pwd_title),
                    color = colorResource(id = R.color.point_pink_color),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = modifier
                .padding(bottom = 20.dp)
                .widthIn(min = 280.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = colorResource(id = R.color.point_pink_color),
                focusedBorderColor = colorResource(id = R.color.point_pink_color),
                unfocusedLeadingIconColor = colorResource(id = R.color.point_pink_color)
            )
        )

        Button(
            //modifier = Modifier.weight(1f),
            // the button is enabled when the user makes a selection
            //enabled = selectedValue.isNotEmpty(),
            onClick = {
                onLoginButtonClicked(1)
            },
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
            Text(stringResource(R.string.show_list))
        }
    }
}
