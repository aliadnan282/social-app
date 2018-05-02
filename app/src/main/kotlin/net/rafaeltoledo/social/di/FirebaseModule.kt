package net.rafaeltoledo.social.di

import net.rafaeltoledo.social.data.auth.AuthManager
import net.rafaeltoledo.social.data.firebase.FirebaseAuthManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val firebaseModule = Kodein.Module {

    bind<AuthManager>() with singleton { FirebaseAuthManager() }
}