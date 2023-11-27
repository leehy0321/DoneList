package com.hy.donelist

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hy.donelist.data.DoneListData
import com.hy.donelist.data.DoneListRoomDatabase
import com.hy.donelist.ui.ContentsScreen
import com.hy.donelist.ui.DoneListViewModel
import com.hy.donelist.ui.DoneListViewModelFactory
import com.hy.donelist.ui.ListScreen
import com.hy.donelist.ui.LoginScreenDisplay
import com.hy.donelist.ui.NumberScreen

enum class DoneListScreen {
    Number,
    List,
    Login,
    Contents
}

class DoneListAppInfo(context: Context) {
    val doneListViewModel: DoneListViewModel by lazy {
        DoneListViewModelFactory(DoneListRoomDatabase.getDatabase(context).itemDao()).create(
            DoneListViewModel::class.java
        )
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun DoneListApp(
    context: Context
) {
    val viewModel = DoneListAppInfo(context).doneListViewModel

    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DoneListScreen.Number.name,
        modifier = Modifier
    ) {
        composable(route = DoneListScreen.Number.name) {
            NumberScreen(
                context = context,
                onFinishSettingButtonClicked = {
                    viewModel.setCountNumber(it)
                    navController.navigate(DoneListScreen.List.name)
                }
            )
        }

        composable(route = DoneListScreen.Login.name) {
            LoginScreenDisplay(
                onLoginButtonClicked = {
                    navController.navigate(DoneListScreen.Number.name)
                }
            )
        }

        composable(route = DoneListScreen.List.name) {
            /* TEST */
            val list = listOf(
                DoneListData(
                    date = "2023-11-17",
                    allCount = uiState.numberCount,
                    doneContent = arrayListOf(
                        "Clean up the desk",
                        "Go to school",
                        "Do the homework"
                    )
                ),
                DoneListData(
                    date = "2023-11-16",
                    allCount = uiState.numberCount,
                    doneContent = arrayListOf("ss")
                ),
                DoneListData(
                    date = "2023-11-15",
                    allCount = uiState.numberCount,
                    doneContent = arrayListOf("ss")
                )
            )
            ListScreen(
                allDoneCount = uiState.numberCount,
                doneListData = list,
                onContentManageClickedEvent = {
                    viewModel.setCurrentContents(it)
                    navController.navigate(DoneListScreen.Contents.name)
                })

            /*
            ListScreen(
                doneListData = viewModel.getListDoneList()
            )
            */
        }

        composable(route = DoneListScreen.Contents.name) {
            ContentsScreen(context, content = uiState.currentContent)
        }
    }
}
