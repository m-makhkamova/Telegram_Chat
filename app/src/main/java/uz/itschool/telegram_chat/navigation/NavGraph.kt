package uz.itschool.telegram_chat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uz.itschool.telegram_chat.screens.SplashScreen
import uz.itschool.telegram_chat.screens.SignInScreen

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route)
    {

        composable(route = Screens.Splash.route){
            SplashScreen(navController)
        }
        composable(route = Screens.SignIn.route){
            SignInScreen(navController)
        }
    }
}