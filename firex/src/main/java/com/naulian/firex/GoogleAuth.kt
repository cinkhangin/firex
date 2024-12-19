package com.naulian.firex

object GoogleAuth {
    /*private val TAG = GoogleAuth::class.java.simpleName
    private val auth get() = Firebase.auth
    private var callback: ((Result<String>) -> Unit)? = null

    fun onCallback(data: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        callback?.let { signIn(task.result, it) }
    }

    fun launch(
        client: GoogleSignInClient,
        launcher: ActivityResultLauncher<Intent>,
        action: (Result<String>) -> Unit
    ) {
        val signInIntent = client.signInIntent
        launcher.launch(signInIntent)
        callback = action
    }

    fun createClient(activity: Activity, clientId: String): GoogleSignInClient {
        val method = GoogleSignInOptions.DEFAULT_SIGN_IN
        val signInOptions = GoogleSignInOptions.Builder(method)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(activity, signInOptions)
    }

    private fun signIn(acct: GoogleSignInAccount, action: (Result<String>) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnSuccessListener {
                action(Result.success("Welcome"))
            }
            .addOnFailureListener {
                logDebug(TAG, it.localizedMessage)
                action(failMessage("Failed to login"))
            }
    }*/
}