package juniar.nicolas.pokeapp.presentation.detailpokemon

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import juniar.nicolas.pokeapp.R
import juniar.nicolas.pokeapp.databinding.ActivityDetailPokemonBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingActivity
import juniar.nicolas.pokeapp.utils.Constant.POKEMON_IMAGE_URL
import juniar.nicolas.pokeapp.utils.Util.loadImage
import juniar.nicolas.pokeapp.utils.Util.showToast

@AndroidEntryPoint
class DetailPokemonActivity : BaseViewBindingActivity<ActivityDetailPokemonBinding>() {

    private val viewModel: DetailPokemonViewModel by viewModels()

    private var isBookmarked = false

    companion object {
        const val POKEMON_NAME = "pokemon_name"
    }

    override fun getContentView() = ActivityDetailPokemonBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_24dp)
        setupListener()
        intent.getStringExtra(POKEMON_NAME)?.let {
            viewModel.getDetailPokemon(it)
        }
        viewModel.observeDetailPokemonModel().onChangeValue {
            viewBinding.ivPokemon.loadImage("$POKEMON_IMAGE_URL${it.name}.jpg")
            viewBinding.tvPokemonName.text = it.name
            viewBinding.tvType1.text = it.types[0].type.name
        }
    }

    private fun setupListener() {
        with(viewBinding) {
            ivBookmark.setOnClickListener {
                ivBookmark.setImageResource(
                    if (isBookmarked) {
                        R.drawable.ic_bookmark_24dp
                    } else {
                        R.drawable.ic_bookmark_filled_24dp
                    }
                )
                isBookmarked = !isBookmarked
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}