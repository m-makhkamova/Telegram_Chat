package uz.itschool.telegram_chat.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.itschool.telegram_chat.navigation.NavGraph

@Composable
fun SplashScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Get Closer To Everyone",
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 20.dp

            )
//
//            Text(
//                text = "Helps you to contact everyone with just an easy way",
//                style = MaterialTheme.typography.body1,
//                color = MaterialTheme.colorScheme.onPrimary,
//                modifier = Modifier.padding(bottom = 32.dp)
//            )
//
//                Image(
//                    painter = painterResource(id = R.drawable.ic_logo),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(200.dp)
//                        .clip(MaterialTheme.shapes.medium),
//                    contentScale = ContentScale.Crop
//                )
//            }
//
//            Button(
//                onClick = { /* Handle button click */ },
//                modifier = Modifier
//                    .padding(top = 16.dp)
//                    .fillMaxWidth()
//            ) {
//                Text(text = "Get Started")
//            }
//        }
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


