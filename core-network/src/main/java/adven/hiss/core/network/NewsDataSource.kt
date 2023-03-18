package adven.hiss.core.network

import adven.hiss.core.network.model.Article
import adven.hiss.core.network.model.NewsResponse
import adven.hiss.core.network.resource.Headline
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import javax.inject.Inject

interface NewsDataSource {

    suspend fun getHeadlines(country: String, pageSize: Int, page: Int): List<Article>

    suspend fun getByCategory(
        country: String,
        category: String,
        pageSize: Int,
        page: Int
    ): List<Article>

}

class NewsDataSourceImpl @Inject constructor(private val httpClient: HttpClient) : NewsDataSource {

    override suspend fun getHeadlines(country: String, pageSize: Int, page: Int): List<Article> {
        return httpClient.get(Headline(country, pageSize, page))
            .body<NewsResponse>()
            .articles.orEmpty()
    }

    override suspend fun getByCategory(
        country: String,
        category: String,
        pageSize: Int,
        page: Int
    ): List<Article> {
        return httpClient.get(Headline(country, pageSize, page, category))
            .body<NewsResponse>()
            .articles.orEmpty()
    }


}