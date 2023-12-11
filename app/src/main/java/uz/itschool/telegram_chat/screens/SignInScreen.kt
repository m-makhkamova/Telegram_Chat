package uz.itschool.telegram_chat.screens

import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import uz.itschool.telegram_chat.R
import uz.itschool.telegram_chat.navigation.NavGraph


private lateinit var auth: FirebaseAuth
private lateinit var googleSignInClient: GoogleSignInClient

fun init(context: Context) {
    // Initialize FirebaseAuth
    auth = FirebaseAuth.getInstance()

    // Configure Google Sign-In
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(context, gso)
}

fun signInWithGoogle(activityLauncher: ActivityResultLauncher<Intent>) {
    val signInIntent = googleSignInClient.signInIntent
    activityLauncher.launch(signInIntent)
}

fun handleSignInResult(data: Intent?, onComplete: (Boolean) -> Unit) {
    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
    try {
        val account = task.getResult(ApiException::class.java)
        firebaseAuthWithGoogle(account, onComplete)
    } catch (e: ApiException) {
        Log.w("GoogleSignIn", "Google sign-in failed", e)
        onComplete(false)
    }
}


@Composable
fun SignInScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp, 20.dp, 0.dp, 0.dp)
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
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Happy to see you again, to use your account please login first, using your Google account.",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
                    .width(300.dp),
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal
            )
            Image(
                painter = painterResource(id = R.drawable.signin),
                contentDescription = "SignIn image",
                modifier = Modifier
                    .height(450.dp)
                    .width(350.dp)
                    .padding(30.dp, 30.dp, 0.dp, 10.dp)
            )

            Button(onClick = {
                private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?, onComplete: (Boolean) -> Unit) {
                    val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener { signInTask ->
                            if (signInTask.isSuccessful) {
                                navController.navigate("signin_screen")
                                onComplete(true)
                            } else {
                                Log.w("GoogleSignIn", "signInWithCredential:failure", signInTask.exception)
                                onComplete(false)
                            }
                        }
                }
            }, modifier = Modifier
                .width(300.dp)
                .height(45.dp)
                .padding(0.dp, 0.dp, 0.dp, 0.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF771F98))) {
                Text(text = "Sign in with Google", fontSize = 18.sp, fontFamily = poppinsFamily, fontWeight = FontWeight.Normal)
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
