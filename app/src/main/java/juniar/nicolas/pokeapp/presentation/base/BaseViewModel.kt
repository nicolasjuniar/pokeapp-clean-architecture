package juniar.nicolas.pokeapp.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val isError = MutableLiveData<Boolean>()
    protected val isLoading = MutableLiveData<Boolean>()
    protected val isSuccess = MutableLiveData<Boolean>()
    protected val message = MutableLiveData<String>()

    fun observeError(): LiveData<Boolean> = isError
    fun observeLoading(): LiveData<Boolean> = isLoading
    fun observeSuccess(): LiveData<Boolean> = isSuccess
    fun observeMessage(): LiveData<String> = message
}