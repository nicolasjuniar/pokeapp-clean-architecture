package juniar.nicolas.pokeapp.data.remote.api

import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("abilities")
    val abilities: List<Ability>,
    @SerializedName("types")
    val types: List<TypeSlot>
) {
    fun getFirstType() = types[0].type.name
}

data class Ability(
    @SerializedName("ability")
    val detailAbility: DetailAbility,
    @SerializedName("is_hidden")
    val isHidden: Boolean
)

data class DetailAbility(
    @SerializedName("name")
    val name: String
)

data class TypeSlot(
    @SerializedName("type")
    val type: Type
)

data class Type(
    @SerializedName("name")
    val name: String
)