package juniar.nicolas.pokeapp.presentation.dashboard

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import juniar.nicolas.pokeapp.data.local.datastore.PreferenceManager
import juniar.nicolas.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val preferenceManager: PreferenceManager) :
    BaseViewModel() {

    private val _isSuccessLogout = MutableSharedFlow<Boolean>()
    val isSuccessLogout = _isSuccessLogout.asSharedFlow()

    fun logout() {
        viewModelScope.launch {
            preferenceManager.saveLoggedUsername("")
            _isSuccessLogout.emit(true)
        }
    }
}