package juniar.nicolas.pokeapp.presentation.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import juniar.nicolas.pokeapp.data.PokemonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val pokemonRepository: PokemonRepository):ViewModel() {

    fun getPokemon() {
        viewModelScope.launch {
            try {
                val response = pokemonRepository.getPokemon(0)
                Log.d("Pokemon",response.result.toString())
            } catch (e:Exception) {

            }
        }
    }
}