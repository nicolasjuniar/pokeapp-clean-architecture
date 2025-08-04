package juniar.nicolas.pokeapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import juniar.nicolas.pokeapp.presentation.common.ProgressBarDialog
import juniar.nicolas.pokeapp.utils.Util.isVisible
import juniar.nicolas.pokeapp.utils.Util.showToast

abstract class BaseViewBindingFragment<VB:ViewBinding>:Fragment() {

    protected val progressBarDialog by lazy {
        ProgressBarDialog(requireActivity())
    }

    private var _viewBinding: VB? = null
    protected val viewBinding get() = _viewBinding!!

    abstract fun getContentView(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = getContentView(inflater, container)
        return viewBinding.root
    }

    protected fun <T> LiveData<T>.onChangeValue(action: (T) -> Unit) {
        observe(this@BaseViewBindingFragment) { data -> data?.let(action) }
    }

    open fun observeLoadingViewModel(viewModel: BaseViewModel) {
        viewModel.observeLoading().onChangeValue {
            progressBarDialog.isVisible(it)
        }
    }

    open fun observeMessageViewModel(viewModel: BaseViewModel) {
        viewModel.observeMessage().onChangeValue {
            requireActivity().showToast(it)
        }
    }

    protected fun observeViewModel(viewModel: BaseViewModel) {
        with(viewModel) {
            observeLoadingViewModel(this)
            observeMessageViewModel(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}