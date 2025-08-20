package juniar.nicolas.pokeapp.presentation.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.R
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
                if (authViewModel.authMode.value != AuthMode.LOGIN) {
                    authViewModel.setAuthMode(AuthMode.LOGIN)
                }
            }
            tvRegister.setOnClickListener {
                if (authViewModel.authMode.value != AuthMode.REGISTER) {
                    authViewModel.setAuthMode(AuthMode.REGISTER)
                }
            }
            btnAction.setOnClickListener {
                if (authViewModel.authMode.value == AuthMode.LOGIN) {
                    authViewModel.login()
                } else {
                    authViewModel.register()
                }
            }
            etUsername.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    authViewModel.onUsernameChanged(p0.toString())
                }
            })
            etPassword.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    authViewModel.onPasswordChanged(p0.toString())
                }
            })
            etConfirmPassword.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    authViewModel.onConfirmPasswordChanged(p0.toString())
                }
            })
        }
    }

    private fun collectFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    authViewModel.isSuccess.collect {
                        if (it) {
                            openActivity<DashboardActivity>(isFinish = true)
                        } else {
                            clearField()
                        }
                    }
                }
                launch {
                    authViewModel.message.collect {
                        showToast(it)
                    }
                }
                launch {
                    authViewModel.authMode.collect {
                        if(it == AuthMode.LOGIN) {
                            setLoginMode()
                        } else {
                            setRegisterMode()
                        }
                    }
                }
                launch {
                    authViewModel.isFormValid.collect {
                        viewBinding.btnAction.isEnabled = it
                    }
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

    private fun setLoginMode() {
        clearField()
        with(viewBinding) {
            fieldConfirmPassword.gone()
            btnAction.text = "Login"
            tvLogin.setTextColor(ContextCompat.getColor(this@AuthActivity, R.color.white))
            tvLogin.setBackgroundResource(R.drawable.rounded_left_active)
            tvRegister.setTextColor(ContextCompat.getColor(this@AuthActivity,R.color.primary_red))
            tvRegister.setBackgroundResource(R.drawable.rounded_right_inactive)
        }
    }

    private fun setRegisterMode() {
        with(viewBinding) {
            clearField()
            fieldConfirmPassword.visible()
            btnAction.text = "Register"
            tvLogin.setTextColor(ContextCompat.getColor(this@AuthActivity, R.color.primary_red))
            tvLogin.setBackgroundResource(R.drawable.rounded_left_inactive)
            tvRegister.setTextColor(ContextCompat.getColor(this@AuthActivity,R.color.white))
            tvRegister.setBackgroundResource(R.drawable.rounded_right_active)
        }
    }
}