package juniar.nicolas.pokeapp.presentation.detailpokemon

import android.os.Bundle
import android.view.MenuItem
import juniar.nicolas.pokeapp.R
import juniar.nicolas.pokeapp.databinding.ActivityDetailPokemonBinding
import juniar.nicolas.pokeapp.presentation.base.BaseViewBindingActivity
import juniar.nicolas.pokeapp.utils.Util.showToast

class DetailPokemonActivity : BaseViewBindingActivity<ActivityDetailPokemonBinding>() {

    companion object {
        const val POKEMON_NAME = "pokemon_name"
    }

    override fun getContentView() = ActivityDetailPokemonBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_24dp)
        showToast(intent.getStringExtra(POKEMON_NAME).orEmpty())
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