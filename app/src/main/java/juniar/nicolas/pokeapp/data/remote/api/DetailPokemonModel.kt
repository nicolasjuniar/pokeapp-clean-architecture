package juniar.nicolas.pokeapp.data.remote.api

import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String,
    @SerializedName("abilities")
    private val abilities: List<Ability>,
    @SerializedName("types")
    private val types: List<TypeSlot>
)

data class Ability(
    @SerializedName("name")
    private val name: String
)

data class TypeSlot(
    @SerializedName("type")
    private val type: Type
)

data class Type(
    @SerializedName("name")
    private val name: String
)