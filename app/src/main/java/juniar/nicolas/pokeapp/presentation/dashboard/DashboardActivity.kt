package juniar.nicolas.pokeapp.presentation.dashboard

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import juniar.nicolas.pokeapp.R
import juniar.nicolas.pokeapp.databinding.ActivityDashboardBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingActivity

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