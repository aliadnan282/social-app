package net.rafaeltoledo.social.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import kotlin.reflect.KClass

abstract class BaseActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: ViewModelProvider.Factory by instance()

    protected fun <T : ViewModel> getViewModelInstance(kClass: KClass<T>) =
            ViewModelProviders.of(this, viewModelFactory)
                    .get(kClass.java)
}