package uz.itschool.telegram_chat.screens

import android.content.Intent
import android.service.autofill.UserData
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.autoSaver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import uz.itschool.telegram_chat.R
import uz.itschool.telegram_chat.navigation.NavGraph

@Composable
fun SignInScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        val auth:FirebaseAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignInOptions.(this, gs)

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

            Spacer(modifier = Modifier.height(50.dp))
            Icon(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back icon",
                modifier = Modifier
                    .size(22.dp)
            )
            Text(
                text = "Hello, Welcome Back",
                fontSize = 22.sp,
                modifier = Modifier.padding(30.dp, 30.dp, 0.dp, 0.dp),
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Happy to see you again, to use your account please login first, using your Google account.",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(30.dp, 0.dp, 0.dp, 0.dp)
                    .width(300.dp),
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal
            )
            Image(
                painter = painterResource(id = R.drawable.signin),
                contentDescription = "SignIn image",
                modifier = Modifier
                    .height(400.dp)
                    .width(300.dp)
                    .padding(200.dp, 0.dp, 0.dp, 0.dp)
            )

            Button(onClick = {
                navController.navigate("signin_screen")
                val signInIntent = mGoogleSignInClient.signInIntent
                startActivityForResult(signInIntent, 1)
            }, modifier = Modifier
                .width(300.dp)
                .height(45.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF771F98))) {
                Text(text = "Sign in with Google", fontSize = 18.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
            }
            Button(onClick = {
                mGoogleSignInClient.signOut()

            }) {
                Text(text = "Sign Out")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testSignIn() {
    val navController = rememberNavController()
    NavGraph(navController = (navController))
    SignInScreen(navController = navController)
}

@Composable
overr fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == 1) {
        val task = GoogleSignInOptions.(data)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account.idToken)
            Log.d("TAG", "onActivityResult: All done")
        } catch (e: ApiException) {
            Log.d("TAG", "error:Authentication failed!")
        }
    }
}


@Composable
private fun firebaseAuthWithGoogle(idToken: String?) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                val user = auth.currentUser
                val userData = UserData(
                    user?.displayName,
                    user?.uid,
                    user?.email,
                    user?.photoUrl.toString()
                )
                setUser(userData)

            } else {
                Log.d("TAG", "error: Authentication Failed.")
            }
        }
}


@Composable
private fun setUser(userData: UserData) {
    userData?.run {
        val userIdReference = Firebase.database.reference
            .child("users").child(userData.uid?:"")
        userIdReference.setValue(userData)
    }
}


