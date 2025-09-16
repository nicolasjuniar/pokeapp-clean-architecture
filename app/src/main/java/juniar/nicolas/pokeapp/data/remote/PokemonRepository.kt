package juniar.nicolas.pokeapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import juniar.nicolas.pokeapp.data.remote.api.PokemonApi
import juniar.nicolas.pokeapp.data.paging.PokemonPagingSource
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    fun getPokemons(): Pager<Int, PokemonModel> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonPagingSource(pokemonApi) }
        )
    }

    suspend fun getDetailPokemon() = pokemonApi.getDetailPokemon("")
}