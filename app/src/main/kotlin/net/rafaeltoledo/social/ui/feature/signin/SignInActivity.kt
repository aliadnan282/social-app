package net.rafaeltoledo.social.ui.feature.signin

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import net.rafaeltoledo.social.R
import net.rafaeltoledo.social.data.auth.Status
import net.rafaeltoledo.social.databinding.ActivitySignInBinding
import net.rafaeltoledo.social.ui.BaseActivity
import net.rafaeltoledo.social.ui.feature.main.MainActivity

class SignInActivity : BaseActivity() {

    private lateinit var binding: ActivitySignInBinding

    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        viewModel = getViewModelInstance(SignInViewModel::class)

        binding.buttonGoogleSignIn.setOnClickListener { viewModel.googleSignIn(this) }

        observeAuthClient()
        observeViewState()
    }

    private fun observeAuthClient() {
        viewModel.authClient.observe(this, Observer {
            it?.signIn(this)
        })

        viewModel.user.observe(this, Observer {
            it?.let {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    private fun observeViewState() {
        viewModel.loading.observe(this, Observer {
            binding.progress.visibility = if (it!!) View.VISIBLE else View.GONE
            binding.buttonGoogleSignIn.isEnabled = it != true
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result = viewModel.authClient.value?.onResult(requestCode, resultCode, data)
        if (result?.status == Status.SUCCESS) {
            viewModel.signIn(result.token!!)
        } else {
            viewModel.loading.value = false
            Snackbar.make(binding.root, R.string.error_sign_in, Snackbar.LENGTH_LONG).show()
        }
    }
}