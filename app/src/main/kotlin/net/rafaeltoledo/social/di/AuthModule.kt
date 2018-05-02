package net.rafaeltoledo.social.di

import net.rafaeltoledo.social.data.auth.DelegatedAuth
import net.rafaeltoledo.social.data.auth.GoogleAuth
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

val authModule = Kodein.Module {

    bind<DelegatedAuth>() with instance(GoogleAuth())
}