package net.rafaeltoledo.social.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.Deferred
import net.rafaeltoledo.social.data.User
import net.rafaeltoledo.social.data.auth.AuthManager

class FirebaseAuthManager : AuthManager {

    private val auth = FirebaseAuth.getInstance()

    override fun googleSignIn(token: String): Deferred<User> {
        val deferred = CompletableDeferred<User>()

        auth.signInWithCredential(GoogleAuthProvider.getCredential(token, null))
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        deferred.complete(User(it.result.user.uid))
                    } else {
                        deferred.completeExceptionally(it.exception!!)
                    }
                }

        return deferred
    }

    override fun isUserLoggedIn() = auth.currentUser != null
}