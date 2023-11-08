package com.hy.donelist.ui

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hy.donelist.R
import com.hy.donelist.ui.ui.theme.DoneListTheme

@Composable
fun NumberScreen(
    modifier: Modifier = Modifier,
    onFinishSettingButtonClicked: (Int) -> Unit,
    context: Context){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var countNumber by remember { mutableStateOf(5) }
        val triangleShape = TriangleShape()
        Text(
            text = stringResource(R.string.number_title),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.point_pink_color),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.number_help),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.help_grey_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 74.dp)
        )

        Button(
            //modifier = Modifier.weight(1f),
            // the button is enabled when the user makes a selection
            //enabled = selectedValue.isNotEmpty(),
            onClick = {
                if(countNumber < 5) countNumber++
                else Toast.makeText(context, R.string.number_setting_warning_message_upper_5, Toast.LENGTH_SHORT).show()
            },
            modifier = modifier
                .widthIn(min = 10.dp)
                .padding(bottom = 11.dp),
            shape = triangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContentColor = Color.LightGray,
                contentColor = colorResource(id = R.color.point_pink_color)
            )
        ) {
        }

        Text(
            text = "$countNumber",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 11.dp)
        )

        Button(
            // the button is enabled when the user makes a selection
            //enabled = countNumber > 1,
            onClick = {
                if(countNumber > 1) countNumber--
                else Toast.makeText(context, R.string.number_setting_warning_message_under_1, Toast.LENGTH_SHORT).show()
            },
            modifier = modifier
                .widthIn(min = 10.dp)
                .padding(bottom = 74.dp)
                .graphicsLayer(rotationZ = 180f), // rotate the triangle button by 180 degrees
            shape = triangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContentColor = Color.LightGray,
                contentColor = colorResource(id = R.color.point_pink_color),
                disabledContainerColor = Color.White
            ),
        ) {
        }

        Button(
            // the button is enabled when the user makes a selection
            //enabled = countNumber > 0,
            onClick = {onFinishSettingButtonClicked(countNumber)},
            modifier = modifier
                .widthIn(min = 200.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContentColor = Color.LightGray,
                contentColor = colorResource(id = R.color.point_pink_color),
                disabledContainerColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.finish_number_setting),
                fontSize = 20.sp
            )
        }
    }
}

// Custom Shape for Triangle
private class TriangleShape : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}