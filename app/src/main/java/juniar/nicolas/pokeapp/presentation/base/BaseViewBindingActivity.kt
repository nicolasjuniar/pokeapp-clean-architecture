package juniar.nicolas.pokeapp.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import juniar.nicolas.pokeapp.presentation.common.ProgressBarDialog

abstract class BaseViewBindingActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val viewBinding: VB by lazy {
        getContentView()
    }

    private lateinit var progressBarDialog: ProgressBarDialog

    abstract fun getContentView(): VB

    abstract fun onViewReady(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        progressBarDialog = ProgressBarDialog(this)
        onViewReady(savedInstanceState)
    }

    protected fun <T> LiveData<T>.onChangeValue(action: (T) -> Unit) {
        observe(this@BaseViewBindingActivity) { data -> data?.let(action) }
    }
}