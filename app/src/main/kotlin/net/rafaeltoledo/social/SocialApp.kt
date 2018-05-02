package net.rafaeltoledo.social

import android.app.Application
import net.rafaeltoledo.social.di.authModule
import net.rafaeltoledo.social.di.firebaseModule
import net.rafaeltoledo.social.di.viewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule

open class SocialApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidModule(this@SocialApp))
        import(firebaseModule)
        import(authModule)
        import(viewModelModule)
    }
}