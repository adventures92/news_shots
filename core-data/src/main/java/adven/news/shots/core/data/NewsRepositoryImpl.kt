package adven.news.shots.core.data

import adven.hiss.core.network.NewsDataSource
import adven.news.shots.core.data.mapper.ArticleMapper
import adven.news.shots.core.data.mapper.NewsDataMapper
import adven.news.shots.core.data.mapper.NewsEntityMapper
import adven.news.shots.core.data.model.NewsData
import adven.news.shots.core.data.model.NewsType
import adven.news.shots.core.data.paging.NewsMediator
import adven.news.shots.core.database.DbTransactionUseCase
import adven.news.shots.core.database.dao.NewsDao
import adven.news.shots.core.database.dao.StorageDao
import android.util.Log
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val transactionUseCase: DbTransactionUseCase,
    private val newsSource: NewsDataSource,
    private val storageDao: StorageDao,
    private val newsDao: NewsDao,
    private val articleMapper: ArticleMapper,
    private val newsMapper: NewsDataMapper,
    private val newsEntityMapper: NewsEntityMapper,
) : NewsRepository {

    private val tag = "NewsRepository"

    @OptIn(ExperimentalPagingApi::class)
    override fun fetchNews(
        type: NewsType,
        country: String,
        pageSize: Int
    ): Flow<PagingData<NewsData>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
            ),
            remoteMediator = NewsMediator(
                type = type,
                country = country,
                newsDao = newsDao,
                newsSource = newsSource,
                mapper = articleMapper,
                storageDao = storageDao,
                transactionUseCase = transactionUseCase
            ),
        ) {
            newsDao.pagingSource(type.type)
        }.flow.distinctUntilChanged().map { page ->
            page.map(newsMapper::invoke)
        }
    }

    override fun fetchBookmarks(pageSize: Int): Flow<PagingData<NewsData>> {
        return Pager(
            config = PagingConfig(pageSize)
        ) {
            newsDao.pagingBookmark()
        }.flow.distinctUntilChanged().map { page ->
            page.map(newsMapper::invoke)
        }
    }

    override suspend fun toggleBookMark(newsData: NewsData): Boolean {
        return try {
            newsDao.updateNews(newsEntityMapper(newsData.copy(isBookmarked = newsData.isBookmarked.not())))
            true
        } catch (e: Exception) {
            Log.e(tag, "add bookmark", e)
            false
        }
    }

    override suspend fun refreshHeadlines(country: String): Boolean {
        return try {
            val news = newsSource.getHeadlines(country, 20, 1).map {
                articleMapper(it)
            }
            newsDao.clearAll("headline")
            newsDao.insertAll(news)
            true
        } catch (e: Exception) {
            Log.e(tag, "refresh news", e)
            false
        }
    }
}