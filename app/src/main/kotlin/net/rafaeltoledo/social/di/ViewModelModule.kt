package net.rafaeltoledo.social.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import net.rafaeltoledo.social.ui.BaseViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val viewModelModule = Kodein.Module {

    bind<ViewModelProvider.Factory>() with singleton {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                val newInstance = modelClass.newInstance()

                if (BaseViewModel::class.java.isInstance(newInstance)) {
                    (newInstance as BaseViewModel).whenReady(kodein)
                }

                return newInstance
            }
        }
    }
}