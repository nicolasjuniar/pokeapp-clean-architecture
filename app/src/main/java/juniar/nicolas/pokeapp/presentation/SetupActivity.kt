package juniar.nicolas.pokeapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.data.local.datastore.PreferenceManager
import juniar.nicolas.pokeapp.presentation.auth.AuthActivity
import juniar.nicolas.pokeapp.presentation.dashboard.DashboardActivity
import juniar.nicolas.pokeapp.utils.Util.openActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SetupActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            if (preferenceManager.getLoggedUsername().isEmpty()) {
                openActivity<AuthActivity>(isFinish = true)
            } else {
                openActivity<DashboardActivity>(isFinish = true)
            }
        }
    }
}