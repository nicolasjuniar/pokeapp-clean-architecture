package juniar.nicolas.pokeapp.presentation.auth

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.databinding.ActivityAuthBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingActivity
import juniar.nicolas.pokeapp.presentation.dashboard.DashboardActivity
import juniar.nicolas.pokeapp.utils.Util.openActivity
import juniar.nicolas.pokeapp.utils.Util.showToast

@AndroidEntryPoint
class AuthActivity : BaseViewBindingActivity<ActivityAuthBinding>() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun getContentView() = ActivityAuthBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        viewBinding.btnAction.setOnClickListener {
            showToast("open dashboard")
            openActivity<DashboardActivity>()
        }
    }
}