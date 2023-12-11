package uz.itschool.telegram_chat.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.telegram_chat.navigation.NavGraph

fun contactScreen(navController: NavController){}

@Composable
@Preview(showBackground = true)
fun testContacts(){
    val navController = rememberNavController()
    NavGraph(navController = (navController))
    SignInScreen(navController = navController)
}