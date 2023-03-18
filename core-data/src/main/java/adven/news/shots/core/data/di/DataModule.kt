package adven.news.shots.core.data.di

import adven.news.shots.core.data.NewsRepository
import adven.news.shots.core.data.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindNewsRepo(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}