package uz.itschool.telegram_chat.navigation

sealed class Screens(val route:String){
    object Splash:Screens("splash_screen")
    object Register:Screens("register_screen")
    object Login:Screens("login_screen")
    object Main:Screens("main_screen")

}