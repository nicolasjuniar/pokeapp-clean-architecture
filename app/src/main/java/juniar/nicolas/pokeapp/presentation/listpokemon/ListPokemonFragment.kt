package juniar.nicolas.pokeapp.presentation.listpokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import juniar.nicolas.pokeapp.databinding.FragmentListPokemonBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingFragment

class ListPokemonFragment : BaseViewBindingFragment<FragmentListPokemonBinding>() {

    override fun getContentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListPokemonBinding.inflate(layoutInflater, container, false)
}