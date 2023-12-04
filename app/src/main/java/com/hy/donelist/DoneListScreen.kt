package com.hy.donelist

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
            val doneList: List<DoneListData> by viewModel.allItems.observeAsState(emptyList())
            ListScreen(
                allDoneCount = uiState.numberCount,
                doneListData = doneList,
                onContentManageClickedEvent = {
                    viewModel.setCurrentContents(it)
                    navController.navigate(DoneListScreen.Contents.name)
                })
        }

        composable(route = DoneListScreen.Contents.name) {
            ContentsScreen(context, content = uiState.currentContent)
        }
    }
}
