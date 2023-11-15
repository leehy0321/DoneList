package com.hy.donelist

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hy.donelist.ui.DoneListViewModel
import com.hy.donelist.ui.ListScreen
import com.hy.donelist.ui.LoginScreenDisplay
import com.hy.donelist.ui.NumberScreen

enum class DoneListScreen {
    Number,
    List,
    Login
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun DoneListApp(
    viewModel: DoneListViewModel = viewModel(),
    context: Context
) {
    val navController = rememberNavController()
        NavHost(navController = navController,
            startDestination = DoneListScreen.Number.name,
            modifier = Modifier){

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
                ListScreen(
                    doneListData = viewModel.getListDoneList()
                )
            }

        }

}
