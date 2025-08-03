package juniar.nicolas.pokeapp.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("result") val result:List<PokemonModel>
)