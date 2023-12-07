package uz.itschool.telegram_chat.navigation

sealed class Screens(val route:String){
    object Splash:Screens("splash_screen")
    object SignIn:Screens("signin_screen")
    object Main:Screens("main_screen")

}