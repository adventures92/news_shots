package adven.news.shots.core.database.di

import adven.news.shots.core.database.NewsDb
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideNewsDao(newsDb: NewsDb) = newsDb.newsDao()

    @Provides
    fun provideStorageDao(newsDb: NewsDb) = newsDb.storageDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): NewsDb {
        return Room.databaseBuilder(
            appContext,
            NewsDb::class.java,
            "MyModel"
        ).build()
    }
}
