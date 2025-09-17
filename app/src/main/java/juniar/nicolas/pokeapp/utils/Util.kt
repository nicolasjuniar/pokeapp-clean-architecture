package juniar.nicolas.pokeapp.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import juniar.nicolas.pokeapp.R

object Util {

    inline fun <reified T : Activity> Activity.openActivity(
        bundle: Bundle? = null,
        isFinishAffinity: Boolean = false,
        isFinish: Boolean = false,
        isClearTopSingleTop: Boolean = false
    ) {
        val intent = Intent(this, T::class.java)
        if (isClearTopSingleTop) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        if (isFinish) {
            finish()
        }
        if (isFinishAffinity) {
            finishAffinity()
        }
    }

    fun Context.showToast(text: Any) {
        Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT)
            .show()
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

    fun View.visible() {
        this.visibility = View.VISIBLE
        this.isEnabled = true
    }

    fun Dialog.isVisible(visible: Boolean) {
        if (visible) {
            this.show()
        } else {
            this.dismiss()
        }
    }

    fun ImageView.loadImage(
        url: String
    ) {
        Glide.with(this.context)
            .load(url)
            .fitCenter()
            .into(this)
    }

    fun Int.toPokedexNumber() =
        when (this.toString().length) {
            1 -> "#000${this + 1}"
            2 -> "#00${this + 1}"
            3 -> "#0${this + 1}"
            else -> "#${this + 1}"
        }

    fun String.getPokemonTypeColor() = when (this) {
        "normal" -> R.color.type_normal
        "fire" -> R.color.type_fire
        "water" -> R.color.type_water
        "electric" -> R.color.type_electric
        "grass" -> R.color.type_grass
        "ice" -> R.color.type_ice
        "fighting" -> R.color.type_fighting
        "poison" -> R.color.type_poison
        "ground" -> R.color.type_ground
        "flying" -> R.color.type_flying
        "psychic" -> R.color.type_psychic
        "bug" -> R.color.type_bug
        "rock" -> R.color.type_rock
        "ghost" -> R.color.type_ghost
        "dragon" -> R.color.type_dragon
        "dark" -> R.color.type_dark
        "steel" -> R.color.type_steel
        else -> R.color.type_fairy
    }
}