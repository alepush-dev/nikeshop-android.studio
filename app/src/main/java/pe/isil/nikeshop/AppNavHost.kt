package pe.isil.nikeshop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.isil.nikeshop.features.main.MainScreen
import pe.isil.nikeshop.features.onboarding.presentation.OnBoardingScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "onboarding") {

        composable("onboarding") {
            OnBoardingScreen(
                onNextClick = {
                    navController.navigate("main") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            MainScreen()
        }
    }
}