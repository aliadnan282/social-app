package net.rafaeltoledo.social.ui.feature.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import net.rafaeltoledo.social.R
import net.rafaeltoledo.social.data.auth.AuthManager
import net.rafaeltoledo.social.databinding.ActivityMainBinding
import net.rafaeltoledo.social.ui.BaseActivity
import net.rafaeltoledo.social.ui.feature.signin.SignInActivity
import org.kodein.di.generic.instance

class MainActivity : BaseActivity() {

    private val auth: AuthManager by instance()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        if (!auth.isUserLoggedIn()) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}