package com.hy.donelist.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hy.donelist.DoneListAppInfo
import com.hy.donelist.R
import com.hy.donelist.data.DoneListData
import com.hy.donelist.ui.ui.theme.DoneListTheme

@Composable
fun ContentsScreen(
    context: Context,
    content: DoneListData,
    modifier: Modifier = Modifier
) {
    val viewModel = DoneListAppInfo(context).doneListViewModel
    val uiState by viewModel.uiState.collectAsState()

    viewModel.refreshCurrentContent(content)
    val currentContent = uiState.currentContent
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val currentDoneContent = ArrayList(currentContent.doneContent)
        val needAddDoneContent: Int = currentContent.allCount - currentDoneContent.size
        for (i in 0 until needAddDoneContent) {
            currentDoneContent.add("")
        }

        Text(
            text = currentContent.date,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.point_pink_color),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        LazyColumn(
            modifier
                .fillMaxHeight(0.7f)
                .padding(bottom = 20.dp)
        ) {
            itemsIndexed(currentDoneContent) { index, item ->
                var editedText by remember { mutableStateOf(item) }
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 10.dp)
                        .fillMaxWidth(),
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
                            value = editedText,
                            onValueChange = {
                                editedText = it
                                currentDoneContent[index] = editedText
                            },
                            cursorBrush = SolidColor(Color.Black),
                            textStyle = LocalTextStyle.current.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                currentDoneContent.removeAll { it.isEmpty() }

                if(currentDoneContent.isNotEmpty()) {
                    currentContent.doneContent = currentDoneContent
                    viewModel.refreshCurrentContent(currentContent)
                    viewModel.addDoneListData(currentContent)
                }
            },
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
                text = stringResource(R.string.save_button_title),
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ContentsScreenPreview() {
    DoneListTheme {
        ContentsScreen(
            context = LocalContext.current,
            content =
            DoneListData(
                date = "2023-03-11",
                allCount = 4,
                doneContent = arrayListOf(
                    "Clean up the desk",
                    "Go to school",
                    "Do the homework"
                )
            )
        )
    }
}