package juniar.nicolas.pokeapp.presentation.listpokemon

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import juniar.nicolas.pokeapp.data.PokemonModel
import juniar.nicolas.pokeapp.data.PokemonRepository
import juniar.nicolas.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class ListPokemonViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    BaseViewModel() {

    private val listPokemon = MutableLiveData<List<PokemonModel>>()

    fun observeListPokemon() = listPokemon

    fun getPokemon() {
        viewModelScope.launch {
            try {
                val response = pokemonRepository.getPokemon(0)
                listPokemon.postValue(response.result)
            } catch (e: Exception) {
                message.postValue(e.localizedMessage)
            }
        }
    }
}