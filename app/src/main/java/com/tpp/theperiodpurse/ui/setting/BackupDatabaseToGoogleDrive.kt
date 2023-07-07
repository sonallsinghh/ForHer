package com.tpp.theperiodpurse.ui.setting


import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.tpp.theperiodpurse.ui.component.handleError
import com.tpp.theperiodpurse.ui.component.LoadingScreen
import com.tpp.theperiodpurse.ui.viewmodel.OnboardViewModel
import com.tpp.theperiodpurse.utility.validateUserAuthenticationAndAuthorization

@RequiresApi(Build.VERSION_CODES.O)
@Composable
// TODO Figure out why sometimes database is not backed up
fun BackupDatabase(viewModel: OnboardViewModel,
                   navController: NavHostController,
                   signout: () -> Unit = {},
                   context: Context) {

    val hasBackedUpToGoogleDrive by viewModel.hasBackedUpToGoogleDrive.observeAsState()
    val googleSignedInAccount = GoogleSignIn.getLastSignedInAccount(context)
    val account = googleSignedInAccount?.account
    val hasGoogleDrivePermission = validateUserAuthenticationAndAuthorization(googleSignedInAccount)
    if (account == null) {
        handleSecurityError(context, signout, "ERROR - Please sign in", navController)
    }
    if (!hasGoogleDrivePermission) {
        handleSecurityError(context, signout, "ERROR - Please grant all the required permissions", navController)
    }

    LaunchedEffect(key1 = hasBackedUpToGoogleDrive) {
        if (account != null && !hasBackedUpToGoogleDrive!!) {
            viewModel.backupDatabase(account = account, context)
        } else {
            viewModel.hasBackedUpToGoogleDrive.postValue(false)
            navController.navigate(SettingScreenNavigation.ConfirmBackup.name)
        }
    }
    LoadingScreen()
}

fun handleSecurityError(context: Context, signout: () -> Unit, msg: String, navController:
NavHostController) {
    handleError(context, msg) {
        signout()
        navController.navigate(SettingScreenNavigation.Start.name)
    }
}