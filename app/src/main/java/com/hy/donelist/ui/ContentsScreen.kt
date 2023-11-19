package com.hy.donelist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hy.donelist.R
import com.hy.donelist.data.DoneListData
import com.hy.donelist.ui.ui.theme.DoneListTheme

@Composable
fun ContentsScreen(
    content: DoneListData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val needAddDoneContent: Int = content.allCount - content.doneContent.size
        for (i in 0 until needAddDoneContent) {
            content.doneContent.add("")
        }

        Text(
            text = content.date,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.point_pink_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        LazyColumn(
            modifier
                .fillMaxHeight(0.8f)
                .padding(bottom = 20.dp)
        ) {
            itemsIndexed(content.doneContent) { index, item ->
                var isEditing by remember { mutableStateOf(false) }
                var editedText by remember { mutableStateOf(item) }
                Card(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .clickable {
                            isEditing = true
                        },
                    shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        disabledContainerColor = Color.LightGray,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White
                    )
                ) {
                    Row(
                        modifier = modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BasicTextField(
                            value = TextFieldValue(editedText),
                            onValueChange = {
                                editedText = it.text
                            },
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                                .onFocusChanged {
                                    if (!it.isFocused) {
                                        // Save changes when focus is lost
                                        isEditing = false
                                        content.doneContent[index] = editedText
                                    }
                                }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ContentsScreenPreview() {
    DoneListTheme {
        ContentsScreen(
            DoneListData(
                "2023-03-11",
                4,
                arrayListOf(
                    "Clean up the desk",
                    "Go to school",
                    "Do the homework"
                )
            )
        )
    }
}