package net.rafaeltoledo.social.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

abstract class BaseViewModel : ViewModel(), KodeinAware {

    override lateinit var kodein: Kodein

    val loading = MutableLiveData<Boolean>()

    init {
        loading.postValue(false)
    }

    fun whenReady(kodein: Kodein) {
        this.kodein = kodein
    }
}