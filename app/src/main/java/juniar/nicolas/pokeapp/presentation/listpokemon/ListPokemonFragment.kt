package juniar.nicolas.pokeapp.presentation.listpokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.data.remote.PokemonModel
import juniar.nicolas.pokeapp.databinding.FragmentListPokemonBinding
import juniar.nicolas.pokeapp.databinding.ViewholderPokemonBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingFragment
import juniar.nicolas.pokeapp.presentation.common.DiffCallback
import juniar.nicolas.pokeapp.presentation.common.GeneralRecyclerViewBindingAdapter
import juniar.nicolas.pokeapp.presentation.detailpokemon.DetailPokemonActivity
import juniar.nicolas.pokeapp.presentation.detailpokemon.DetailPokemonActivity.Companion.POKEMON_NAME
import juniar.nicolas.pokeapp.utils.Constant.POKEMON_IMAGE_URL
import juniar.nicolas.pokeapp.utils.Util.onLoad
import juniar.nicolas.pokeapp.utils.Util.openActivity
import juniar.nicolas.pokeapp.utils.Util.showToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListPokemonFragment : BaseViewBindingFragment<FragmentListPokemonBinding>() {

    private val viewModel: ListPokemonViewModel by viewModels()

    private val adapter by lazy {
        PokemonAdapter {
            requireActivity().openActivity<DetailPokemonActivity>(
                bundleOf(
                    POKEMON_NAME to it
                )
            )
        }
    }

    private val pokemonListAdapter by lazy {
        GeneralRecyclerViewBindingAdapter<PokemonModel, ViewholderPokemonBinding>(
            diffCallback = DiffCallback(),
            holderResBinding = {
                ViewholderPokemonBinding.inflate(LayoutInflater.from(requireActivity()), it, false)
            },
            onBind = { pokemon, binding, index ->
                with(binding) {
                    ivPokemon.onLoad(requireActivity(), POKEMON_IMAGE_URL + pokemon.name + ".jpg")
                    tvPokemon.text = pokemon.name
                }
            },
            itemListener = { pokemon, index, _ ->

            }
        )
    }

    override fun getContentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListPokemonBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
        observeData()
//        viewModel.getPokemon()
        lifecycleScope.launch {
            viewModel.pokemons.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setupLayout() {
        with(viewBinding) {
            rvPokemon.adapter = adapter
            rvPokemon.layoutManager = GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun observeData() {
        with(viewModel) {
            observeViewModel(this)
        }
    }
}