package adven.news.shots.core.data

import adven.news.shots.core.data.model.NewsData
import adven.news.shots.core.data.model.NewsType
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun fetchNews(type: NewsType, country: String, pageSize: Int = 20): Flow<PagingData<NewsData>>

    fun fetchBookmarks(pageSize: Int = 20): Flow<PagingData<NewsData>>

    suspend fun toggleBookMark(newsData: NewsData): Boolean

    suspend fun refreshHeadlines(country: String) : Boolean

}