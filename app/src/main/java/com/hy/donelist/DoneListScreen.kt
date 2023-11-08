package com.hy.donelist

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hy.donelist.ui.DoneListViewModel
import com.hy.donelist.ui.ListScreen
import com.hy.donelist.ui.LoginScreenDisplay
import com.hy.donelist.ui.NumberScreen

enum class DoneListScreen() {
    Number,
    List,
    Create,
    Login
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun DoneListAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun DoneListApp(
    modifier: Modifier = Modifier,
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
                        1
                        navController.navigate(DoneListScreen.Number.name)
                    }
                )
            }

            composable(route = DoneListScreen.List.name) {
                ListScreen(
                    countNumber = viewModel.getCountNumber()
                )
            }

        }

}
