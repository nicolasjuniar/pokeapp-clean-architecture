package juniar.nicolas.pokeapp.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import juniar.nicolas.pokeapp.R

class AuthActivity:AppCompatActivity() {

    val fieldUsername:TextInputLayout by lazy {
        findViewById(R.id.field_username)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        fieldUsername.error = ""
    }
}