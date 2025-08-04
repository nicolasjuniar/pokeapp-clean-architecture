package juniar.nicolas.pokeapp.presentation.dashboard

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.R
import juniar.nicolas.pokeapp.databinding.ActivityDashboardBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingActivity

@AndroidEntryPoint
class DashboardActivity : BaseViewBindingActivity<ActivityDashboardBinding>() {
    override fun getContentView() = ActivityDashboardBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        viewBinding.bottomNav
            .setupWithNavController(navController)
    }
}