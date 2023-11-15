package com.hy.donelist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    doneListData: List<DoneListData>
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

        LazyColumn(modifier.fillMaxHeight(0.8f)) {
            items(doneListData) { item ->
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 5.dp)
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
                            modifier = modifier.padding(1.dp),
                            text = item.date
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = modifier.padding(1.dp),
                                textAlign = TextAlign.End,
                                text = "" + item.doneCount
                            )
                            Text(
                                modifier = modifier.padding(3.dp),
                                textAlign = TextAlign.End,
                                text = "/"
                            )
                            Text(
                                modifier = modifier.padding(1.dp),
                                textAlign = TextAlign.End,
                                text = "" + item.allCount
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    DoneListTheme {
        val list = listOf(
            DoneListData("2023-11-17", 5, 1, listOf("ss")),
            DoneListData("2023-11-16", 5, 2, listOf("ss")),
            DoneListData("2023-11-15", 5, 3, listOf("ss"))
        )
        ListScreen(doneListData = list)
    }
}