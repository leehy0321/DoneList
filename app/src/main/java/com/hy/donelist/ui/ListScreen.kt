package com.hy.donelist.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hy.donelist.ui.ui.theme.DoneListTheme

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    countNumber: Int) {
    Text(
        text = "Hello! $countNumber",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    DoneListTheme {
        ListScreen(countNumber = 3)
    }
}