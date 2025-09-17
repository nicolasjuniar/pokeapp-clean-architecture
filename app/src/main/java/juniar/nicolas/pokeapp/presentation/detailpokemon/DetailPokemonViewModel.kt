package juniar.nicolas.pokeapp.presentation.detailpokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import juniar.nicolas.pokeapp.data.remote.PokemonRepository
import juniar.nicolas.pokeapp.data.remote.api.DetailPokemonResponse
import juniar.nicolas.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    BaseViewModel() {

    private val detailPokemonModel = MutableLiveData<DetailPokemonResponse>()

    fun observeDetailPokemonModel() = detailPokemonModel

    fun getDetailPokemon(pokemonName: String) {
        viewModelScope.launch {
            try {
                val response = pokemonRepository.getDetailPokemon(pokemonName)
                detailPokemonModel.postValue(response)
            } catch (e: Exception) {
                message.postValue(e.localizedMessage)
            }
        }
    }
}