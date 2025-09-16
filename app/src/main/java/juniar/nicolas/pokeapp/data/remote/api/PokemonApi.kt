package juniar.nicolas.pokeapp.data.remote.api

import juniar.nicolas.pokeapp.data.remote.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonResponse

    @GET("pokemon/{pokemonName}")
    suspend fun getDetailPokemon(
        @Path("pokemonName") pokemonName: String
    ): DetailPokemonResponse
}