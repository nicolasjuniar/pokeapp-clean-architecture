package juniar.nicolas.pokeapp.presentation.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.R
import juniar.nicolas.pokeapp.databinding.ActivityDashboardBinding
import juniar.nicolas.pokeapp.presentation.auth.AuthActivity
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingActivity
import juniar.nicolas.pokeapp.utils.Util.openActivity
import juniar.nicolas.pokeapp.utils.Util.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity : BaseViewBindingActivity<ActivityDashboardBinding>() {

    private val dashboardViewModel:DashboardViewModel by viewModels()

    override fun getContentView() = ActivityDashboardBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        viewBinding.bottomNav
            .setupWithNavController(navController)

        collectFlow()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_logout) {
            showLogoutDialog()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Yes") { dialog, _ ->
                dashboardViewModel.logout()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun collectFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dashboardViewModel.isSuccessLogout.collect {
                    if(it) {
                        openActivity<AuthActivity>(isFinish = true)
                        showToast("Logout Success")
                    }
                }
            }
        }
    }
}