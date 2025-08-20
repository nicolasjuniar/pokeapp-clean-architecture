package juniar.nicolas.pokeapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "pokeapp_prefs")

class PreferenceManager(private val context: Context) {

    companion object {
        private val LOGGED_USERNAME = stringPreferencesKey("logged_username")
    }

    suspend fun saveLoggedUsername(username: String) {
        context.dataStore.edit {
            it[LOGGED_USERNAME] = username
        }
    }

    suspend fun getLoggedUsername(): String {
        val pref = context.dataStore.data.first()
        return pref[LOGGED_USERNAME] ?: ""
    }
}