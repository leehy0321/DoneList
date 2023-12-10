package com.hy.donelist.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("SimpleDateFormat")
@Composable
fun ListScreen(
    allDoneCount: Int,
    modifier: Modifier = Modifier,
    doneListData: List<DoneListData>,
    onContentManageClickedEvent: (DoneListData) -> Unit
) {
    var selectedOffset = DpOffset.Zero
    var selectedItem by remember { mutableStateOf<DoneListData?>(null) }
    val density = LocalDensity.current
    var itemHeight by remember { mutableStateOf(0.dp) }
    var itemWidth by remember { mutableStateOf(0.dp) }

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
                var isContextMenuVisible by remember { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .onSizeChanged {
                            itemHeight = with(density) { it.height.toDp() }
                            itemWidth = with(density) { it.width.toDp() }
                        }
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
                    Box(modifier = Modifier
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    selectedOffset = DpOffset(it.x.toDp(), it.y.toDp())
                                }
                            )
                        }
                        .combinedClickable(
                            onClick = { onContentManageClickedEvent(item) },
                            onLongClick = {
                                isContextMenuVisible = true
                                selectedItem = item
                            },
                            onLongClickLabel = stringResource(R.string.long_click_string)
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
                    DropdownMenu(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterHorizontally),
                        expanded = isContextMenuVisible,
                        onDismissRequest = { isContextMenuVisible = false },
                        offset = DpOffset(selectedOffset.x, selectedOffset.y)
                    ) {
                        DropdownMenuItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            text = {
                                Text(
                                    text = "Remove",
                                    fontSize = 16.sp,
                                    color = colorResource(id = R.color.point_pink_color),
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            onClick = {
                                isContextMenuVisible = false
                            }
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                val currentDate =
                    SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)

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