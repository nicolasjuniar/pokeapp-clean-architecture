package juniar.nicolas.pokeapp.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import juniar.nicolas.pokeapp.databinding.FragmentFavoriteBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingFragment

class FavoriteFragment : BaseViewBindingFragment<FragmentFavoriteBinding>() {
    override fun getContentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
}