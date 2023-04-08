package com.tpp.theperiodpurse.ui.education


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tpp.theperiodpurse.R

enum class EducationNavigation {
    Learn,
    DYK,
    ProductInfo
}

@Composable
fun EducationScreen(
    outController: NavHostController = rememberNavController(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = EducationNavigation.Learn.name
    ) {
        composable(route = EducationNavigation.Learn.name) {
            EducationScreenLayout(outController, navController)
        }

        composable(EducationNavigation.DYK.name) {
            EducationDYKScreen(navController)
        }

        composable(
            route = EducationNavigation.ProductInfo.name,
            arguments = listOf(navArgument("elementId") { nullable = true })
            ) {
                val elementId =
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>("elementId")

                if (elementId != null) {
                    EducationInfoScreen(navController, elementId)
                }
            }
    }
}

@Composable
fun EducationBackground() {
    Image(painter = painterResource(R.drawable.colourwatercolour),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds)
}