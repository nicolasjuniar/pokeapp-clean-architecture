package juniar.nicolas.pokeapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import juniar.nicolas.pokeapp.data.remote.api.PokemonApi
import juniar.nicolas.pokeapp.data.remote.PokemonModel
import juniar.nicolas.pokeapp.utils.Constant.OFFSET_SIZE

class PokemonPagingSource(
    private val pokemonApi: PokemonApi
) : PagingSource<Int, PokemonModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonModel> {
        return try {
            val offset = params.key ?: 0
            val response = pokemonApi.getPokemon(offset, params.loadSize)

            LoadResult.Page(
                data = response.result,
                prevKey = if (offset == 0) null else offset - OFFSET_SIZE,
                nextKey = if (response.result.isEmpty()) null else offset + OFFSET_SIZE
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonModel>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(OFFSET_SIZE)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(OFFSET_SIZE)
        }
    }
}