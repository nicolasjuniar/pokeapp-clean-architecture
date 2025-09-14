package juniar.nicolas.pokeapp.presentation.listpokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import juniar.nicolas.pokeapp.data.PokemonModel
import juniar.nicolas.pokeapp.databinding.ViewholderPokemonBinding
import juniar.nicolas.pokeapp.utils.Constant.POKEMON_IMAGE_URL
import juniar.nicolas.pokeapp.utils.Util.onLoad
import juniar.nicolas.pokeapp.utils.Util.toPokedexNumber

class PokemonAdapter(private val context: Context) :
    PagingDataAdapter<PokemonModel, PokemonAdapter.UserViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<PokemonModel>() {
        override fun areItemsTheSame(old: PokemonModel, new: PokemonModel) = old.name == new.name
        override fun areContentsTheSame(old: PokemonModel, new: PokemonModel) = old == new
    }

    inner class UserViewHolder(val binding: ViewholderPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonModel: PokemonModel?, index: Int) {
            binding.ivPokemon.onLoad(context, POKEMON_IMAGE_URL + pokemonModel?.name + ".jpg")
            binding.tvPokemon.text = "${index.toPokedexNumber()} ${pokemonModel?.name}"
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ViewholderPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserViewHolder(binding)
    }
}