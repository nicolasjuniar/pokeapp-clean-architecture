package juniar.nicolas.pokeapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import juniar.nicolas.pokeapp.presentation.listpokemon.PokemonPagingSource
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemon(offset: Int, limit: Int = 10) = pokemonApi.getPokemon(offset, limit)

    fun getPokemons(): Pager<Int, PokemonModel> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(pokemonApi) }
        )
    }
}