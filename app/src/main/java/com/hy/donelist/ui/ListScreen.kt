package com.hy.donelist.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hy.donelist.R
import com.hy.donelist.data.DoneListData
import com.hy.donelist.ui.ui.theme.DoneListTheme
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
@Composable
fun ListScreen(
    allDoneCount: Int,
    modifier: Modifier = Modifier,
    doneListData: List<DoneListData>,
    onContentManageClickedEvent: (DoneListData) -> Unit
) {
    Column(
        modifier = modifier
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

        LazyColumn(
            modifier
                .fillMaxHeight(0.7f)
                .padding(bottom = 20.dp)
        ) {
            items(doneListData) { item ->
                Card(
                    onClick = { onContentManageClickedEvent(item) },
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = modifier.padding(start = 5.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = item.date
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.padding(end = 5.dp)
                        ) {
                            Text(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                text = "" + item.doneContent.size
                            )
                            Text(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                text = "/"
                            )
                            Text(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                text = "" + item.allCount
                            )
                        }
                    }
                }
            }
        }
        Button(
            onClick = {
                val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)

                onContentManageClickedEvent(
                    DoneListData(
                        date = currentDate.toString(),
                        allCount = allDoneCount,
                        doneContent = arrayListOf()
                    )
                )
            },
            modifier = modifier.size(90.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                disabledContentColor = Color.LightGray,
                contentColor = colorResource(id = R.color.point_pink_color),
                disabledContainerColor = Color.White
            )
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "content description",
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ListScreenPreview() {
    DoneListTheme {
        val list = listOf(
            DoneListData(
                date = "2023-11-17",
                allCount = 5,
                doneContent = arrayListOf("aa", "bb", "cc")
            ),
            DoneListData(date = "2023-11-16", allCount = 5, doneContent = arrayListOf("ss")),
            DoneListData(date = "2023-11-15", allCount = 5, doneContent = arrayListOf("ss"))
        )
        ListScreen(doneListData = list, allDoneCount = 5, onContentManageClickedEvent = {})
    }
}