package juniar.nicolas.pokeapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import juniar.nicolas.pokeapp.data.local.datastore.PreferenceManager
import juniar.nicolas.pokeapp.data.local.db.UserDao
import juniar.nicolas.pokeapp.data.local.db.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userDao: UserDao,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _isSuccess = MutableSharedFlow<Boolean>()
    val isSuccess: SharedFlow<Boolean> = _isSuccess

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    private val _authMode = MutableStateFlow(AuthMode.LOGIN)

    val authMode = _authMode.asStateFlow()

    private val _username = MutableStateFlow("")

    private val _password = MutableStateFlow("")

    private val _confirmPassword = MutableStateFlow("")

    fun setAuthMode(value: AuthMode) {
        _authMode.value = value
    }

    fun onUsernameChanged(value: String) {
        _username.value = value
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
    }

    fun onConfirmPasswordChanged(value: String) {
        _confirmPassword.value = value
    }

    val isFormValid: Flow<Boolean> = combine(
        _authMode,
        _username,
        _password,
        _confirmPassword
    ) { mode, username, password, confirmPassword ->

        when (mode) {
            AuthMode.LOGIN -> {
                username.isNotBlank() &&
                        password.isNotBlank()
            }

            AuthMode.REGISTER -> {
                username.isNotBlank() &&
                        password.isNotBlank() &&
                        confirmPassword.isNotBlank() &&
                        password.length >= 8 &&
                        password == confirmPassword
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            val username = userDao.login(_username.value, _password.value)
            if (username.isNullOrEmpty()) {
                emitValue(false, "Wrong username or password")
            } else {
                preferenceManager.saveLoggedUsername(_username.value)
                emitValue(true, "Login Success")
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            val username = userDao.getUsername(_username.value)
            if (username.isNullOrEmpty()) {
                insertUser(UserEntity(_username.value, _password.value))
            } else {
                emitValue(false, "Username is taken")
            }
        }
    }

    private suspend fun insertUser(userEntity: UserEntity) {
        val successInsert = userDao.insertUser(userEntity)
        if (successInsert > 0L) {
            preferenceManager.saveLoggedUsername(_username.value)
            emitValue(true, "Success Register")
        } else {
            emitValue(false, "Register Failed")
        }
    }

    private suspend fun emitValue(isSuccess: Boolean, message: String) {
        _isSuccess.emit(isSuccess)
        _message.emit(message)
    }
}

enum class AuthMode { LOGIN, REGISTER }