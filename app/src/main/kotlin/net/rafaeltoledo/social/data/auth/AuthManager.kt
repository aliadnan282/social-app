package net.rafaeltoledo.social.data.auth

import kotlinx.coroutines.experimental.Deferred
import net.rafaeltoledo.social.data.User

interface AuthManager {

    fun googleSignIn(token: String): Deferred<User>

    fun isUserLoggedIn(): Boolean
}