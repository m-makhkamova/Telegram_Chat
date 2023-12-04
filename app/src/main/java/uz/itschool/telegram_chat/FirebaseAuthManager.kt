package uz.itschool.telegram_chat

class FirebaseAuthManager(private val activity: ComponentActivity) {
    private val auth = FirebaseAuth.getInstance()
    private var onSignedIn: (String) -> Unit = {}

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val user = firebaseAuth.currentUser
        user?.let {
            onSignedIn(it.displayName ?: "")
        }
    }

    init {
        auth.addAuthStateListener(authStateListener)
    }

    private val googleSignInLauncher: ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    account?.let {
                        firebaseAuthWithGoogle(it.idToken!!)
                    }
                } catch (e: ApiException) {
                }
            }
        }

    fun signInWithGoogle() {
        val signInIntent = GoogleSignIn.getClient(activity, gso).signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                } else {
                    // If sign in fails
                }
            }
    }

    fun onSignedInListener(callback: (String) -> Unit) {
        onSignedIn = callback
    }
}