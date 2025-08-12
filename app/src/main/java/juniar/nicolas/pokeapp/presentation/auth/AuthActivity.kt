package juniar.nicolas.pokeapp.presentation.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.data.local.UserEntity
import juniar.nicolas.pokeapp.databinding.ActivityAuthBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingActivity
import juniar.nicolas.pokeapp.presentation.dashboard.DashboardActivity
import juniar.nicolas.pokeapp.utils.Util.gone
import juniar.nicolas.pokeapp.utils.Util.openActivity
import juniar.nicolas.pokeapp.utils.Util.showToast
import juniar.nicolas.pokeapp.utils.Util.visible
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity : BaseViewBindingActivity<ActivityAuthBinding>() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun getContentView() = ActivityAuthBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        collectFlow()
        setupListener()
    }

    private fun setupListener() {
        with(viewBinding) {
            tvLogin.setOnClickListener {
                if (btnAction.text != "Login") {
                    clearField()
                    fieldConfirmPassword.gone()
                    btnAction.text = "Login"
                }
            }
            tvRegister.setOnClickListener {
                if (btnAction.text != "Register") {
                    clearField()
                    fieldConfirmPassword.visible()
                    btnAction.text = "Register"
                }
            }
            btnAction.setOnClickListener {
                val userEntity = UserEntity(
                    etUsername.text.toString(),
                    etPassword.text.toString()
                )
                if (btnAction.text == "Login") {
                    authViewModel.login(userEntity)
                } else {
                    authViewModel.register(userEntity)
                }
            }
        }
    }

    private fun collectFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.isSuccess.collect {
                    if (it) {
                        openActivity<DashboardActivity>(isFinish = true)
                    } else {
                        clearField()
                    }
                }
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authViewModel.message.collect {
                    showToast(it)
                }
            }
        }
    }

    private fun clearField() {
        with(viewBinding) {
            etUsername.setText("")
            etPassword.setText("")
            etConfirmPassword.setText("")
        }
    }
}