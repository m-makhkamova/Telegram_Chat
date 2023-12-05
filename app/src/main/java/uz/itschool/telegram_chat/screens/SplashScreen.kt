package uz.itschool.telegram_chat.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uz.itschool.telegram_chat.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.telegram_chat.navigation.NavGraph

@Composable
fun SplashScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val poppinsFamily = FontFamily(
                Font(R.font.poppins_bold, FontWeight.Bold),
                Font(R.font.poppins_black, FontWeight.Black),
                Font(R.font.poppins_light, FontWeight.Normal),
                Font(R.font.poppins_thin, FontWeight.Thin)
            )
               Text(text = "Get Closer to EveryOne", fontSize = 40.sp, modifier = Modifier.padding(40.dp, 70.dp,0.dp,0.dp), fontFamily = poppinsFamily, fontWeight = FontWeight.Bold)
               Text(text = "Helps you to contact everyone with just easy way", fontSize = 15.sp, modifier = Modifier.padding(40.dp, 0.dp, 0.dp, 0.dp), fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
               Image(painter = painterResource(id = R.drawable.splash_img), contentDescription = "Splash image", modifier = Modifier
                   .height(450.dp)
                   .width(450.dp)
                   .padding(30.dp, 50.dp))
               Button(onClick = {}, modifier = Modifier
                   .width(300.dp)
                   .height(45.dp).padding(0.dp, 20.dp, 0.dp, 0.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF771F98))) {
                    Text(text = "Get Started", fontSize = 18.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
               }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testSplash() {
 val navController = rememberNavController()
 NavGraph(navController = (navController))
 SplashScreen(navController = navController)
}


