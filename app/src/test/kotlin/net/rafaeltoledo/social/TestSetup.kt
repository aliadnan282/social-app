package net.rafaeltoledo.social

import android.app.Activity
import android.content.Intent
import kotlinx.coroutines.experimental.Deferred
import net.rafaeltoledo.social.data.User
import net.rafaeltoledo.social.data.auth.AuthManager
import net.rafaeltoledo.social.data.auth.AuthResult
import net.rafaeltoledo.social.data.auth.DelegatedAuth
import net.rafaeltoledo.social.data.auth.Status
import net.rafaeltoledo.social.di.viewModelModule
import org.kodein.di.Kodein
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method

val testModule = Kodein.Module {

    bind<AuthManager>() with singleton {
        object : AuthManager {
            override fun googleSignIn(token: String): Deferred<User> {
                TODO("not implemented")
            }

            override fun isUserLoggedIn() = true
        }
    }

    bind<DelegatedAuth>() with instance(object : DelegatedAuth {
        override fun <T : DelegatedAuth> build(activity: Activity): T {
            TODO("not implemented")
        }

        override fun signIn(activity: Activity) {
            TODO("not implemented")
        }

        override fun onResult(requestCode: Int, resultCode: Int, data: Intent?): AuthResult {
            TODO("not implemented")
        }

        override fun signOut(callback: (Status) -> Unit) {
            TODO("not implemented")
        }
    })
}

class TestSocialApp : SocialApp(), TestLifecycleApplication {

    override fun beforeTest(method: Method?) {
    }

    override fun prepareTest(test: Any?) {
    }

    override fun afterTest(method: Method?) {
    }

    override val kodein = Kodein.lazy {
        import(androidModule(this@TestSocialApp))
        import(testModule)
        import(viewModelModule)
    }
}