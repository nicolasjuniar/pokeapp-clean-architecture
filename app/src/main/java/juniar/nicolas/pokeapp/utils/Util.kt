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

    fun ImageView.onLoad(
        context: Context,
        url: String
    ) {
        Glide.with(context)
            .load(url)
            .into(this)
    }

    fun Int.toPokedexNumber() =
        when (this.toString().length) {
            1 -> "#000${this + 1}"
            2 -> "#00${this + 1}"
            3 -> "#0${this + 1}"
            else -> "#${this + 1}"
        }
}