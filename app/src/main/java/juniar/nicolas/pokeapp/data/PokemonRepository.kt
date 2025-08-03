package juniar.nicolas.pokeapp.data

import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemon(offset: Int, limit: Int = 10) = pokemonApi.getPokemon(offset, limit)
}