package juniar.nicolas.pokeapp.data.remote

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("results") val result:List<PokemonModel>
)