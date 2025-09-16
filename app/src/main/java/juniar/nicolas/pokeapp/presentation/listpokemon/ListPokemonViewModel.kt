package juniar.nicolas.pokeapp.presentation.listpokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import juniar.nicolas.pokeapp.data.remote.PokemonModel
import juniar.nicolas.pokeapp.data.remote.PokemonRepository
import juniar.nicolas.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class ListPokemonViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    BaseViewModel() {

    val pokemons = pokemonRepository.getPokemons()
        .flow
        .cachedIn(viewModelScope)
}