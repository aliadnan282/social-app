package net.rafaeltoledo.social.ui.feature.signin

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.experimental.launch
import net.rafaeltoledo.social.data.User
import net.rafaeltoledo.social.data.auth.AuthManager
import net.rafaeltoledo.social.data.auth.DelegatedAuth
import net.rafaeltoledo.social.ui.BaseViewModel
import org.kodein.di.generic.instance

class SignInViewModel : BaseViewModel() {

    private val auth: AuthManager by instance()
    private val googleAuth: DelegatedAuth by instance()

    val authClient = MutableLiveData<DelegatedAuth>()
    val user = MutableLiveData<User>()

    fun googleSignIn(activity: Activity) {
        loading.value = true

        authClient.value = googleAuth.build(activity)
    }

    fun signIn(token: String) {
        launch {
            user.postValue(auth.googleSignIn(token).await())
        }
    }
}