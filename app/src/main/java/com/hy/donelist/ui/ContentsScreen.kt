package com.hy.donelist.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hy.donelist.data.DoneListData
import com.hy.donelist.ui.ui.theme.DoneListTheme

@Composable
fun ContentsScreen(
    content: DoneListData,
    modifier: Modifier = Modifier) {
    Text(
        text = "Hello " + content.date,
        modifier = modifier
    )
}

@Preview(showBackground = false)
@Composable
fun ContentsScreenPreview() {
    DoneListTheme {
        ContentsScreen(DoneListData("2023-03-11",5, listOf("ss")))
    }
}