package uz.itschool.telegram_chat

fun AuthScreen(authManager: FirebaseAuthManager) {
    var userName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Sign Up with Google Button
        Button(
            onClick = {
                authManager.signInWithGoogle()
            },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Sign Up with Google")
        }

        // Display User Name after Sign In
        if (userName.isNotEmpty()) {
            Text(
                text = "Welcome, $userName!",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }

    // Set up the callback for the signed-in user
    authManager.onSignedInListener { name ->
        userName = name
    }
}

@Composable
fun MyApp(authManager: FirebaseAuthManager) {
    MaterialTheme {
        AuthScreen(authManager = authManager)
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    val authManager = FirebaseAuthManager(LocalContext.current as ComponentActivity)
    MyApp(authManager = authManager)
}