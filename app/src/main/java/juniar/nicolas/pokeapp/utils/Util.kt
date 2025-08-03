package juniar.nicolas.pokeapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

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
}