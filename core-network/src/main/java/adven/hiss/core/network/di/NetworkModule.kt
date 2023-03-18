package adven.hiss.core.network.di

import adven.hiss.core.network.NewsDataSource
import adven.hiss.core.network.NewsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindNewsDataSource(dataSourceImpl: NewsDataSourceImpl) : NewsDataSource

    companion object {

        @Provides
        fun provideKtorClient(): HttpClient {
            return HttpClient(OkHttp) {
                install(Resources)
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
                defaultRequest {
                    url("https://newsapi.org/v2/")
                    bearerAuth("c51513d0d751428bbbac2ae57e074d68")
                }
            }
        }

    }

}