package juniar.nicolas.pokeapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import juniar.nicolas.pokeapp.data.local.datastore.PreferenceManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context: Context) = PreferenceManager(context)
}