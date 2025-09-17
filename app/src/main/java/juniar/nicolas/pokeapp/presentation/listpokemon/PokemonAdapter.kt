package juniar.nicolas.pokeapp.presentation.listpokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import juniar.nicolas.pokeapp.data.remote.PokemonModel
import juniar.nicolas.pokeapp.databinding.ViewholderPokemonBinding
import juniar.nicolas.pokeapp.utils.Constant.POKEMON_IMAGE_URL
import juniar.nicolas.pokeapp.utils.Util.loadImage
import juniar.nicolas.pokeapp.utils.Util.toPokedexNumber

class PokemonAdapter(private val onItemClick: (pokemonName: String) -> Unit) :
    PagingDataAdapter<PokemonModel, PokemonAdapter.UserViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<PokemonModel>() {
        override fun areItemsTheSame(old: PokemonModel, new: PokemonModel) = old.name == new.name
        override fun areContentsTheSame(old: PokemonModel, new: PokemonModel) = old == new
    }

    inner class UserViewHolder(val binding: ViewholderPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonModel: PokemonModel?, index: Int) {
            binding.ivPokemon.loadImage(
                POKEMON_IMAGE_URL + pokemonModel?.name + ".jpg"
            )
            binding.tvPokemon.text = "${index.toPokedexNumber()} ${pokemonModel?.name}"

            binding.root.setOnClickListener {
                onItemClick.invoke(pokemonModel?.name.orEmpty())
            }
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