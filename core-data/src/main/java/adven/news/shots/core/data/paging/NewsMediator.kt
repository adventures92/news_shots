package adven.news.shots.core.data.paging

import adven.hiss.core.network.NewsDataSource
import adven.news.shots.core.data.mapper.ArticleMapper
import adven.news.shots.core.data.model.NewsType
import adven.news.shots.core.database.DbTransactionUseCase
import adven.news.shots.core.database.dao.NewsDao
import adven.news.shots.core.database.dao.StorageDao
import adven.news.shots.core.database.model.NewsEntity
import adven.news.shots.core.database.model.Storage
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator


@OptIn(ExperimentalPagingApi::class)
class NewsMediator(
    private val type: NewsType,
    private val country: String,
    private val newsDao: NewsDao,
    private val storageDao: StorageDao,
    private val newsSource: NewsDataSource,
    private val mapper: ArticleMapper,
    private val transactionUseCase: DbTransactionUseCase,
) : RemoteMediator<Int, NewsEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsEntity>
    ): MediatorResult {
        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastPageKey = transactionUseCase {
                        storageDao.readKey(type.type)?.value
                    }
                    if (lastPageKey.isNullOrBlank()) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    lastPageKey.toIntOrNull() ?: 0
                }
            }

            val pageSize = state.config.pageSize
            val response = if (type is NewsType.Headline)
                newsSource.getHeadlines(country, pageSize, loadKey ?: 1)
            else
                newsSource.getByCategory(country, type.type, pageSize, loadKey ?: 1)

            val data = response?.map {
                mapper(it).copy(category = type.type)
            }.orEmpty()

            val (nextKey, endOfPaging) =
                if (response.isEmpty())
                    "" to true
                else
                    loadKey?.plus(1).toString() to false

            transactionUseCase {
                if (loadType == LoadType.REFRESH) {
                    storageDao.clearStorage(type.type)
                    newsDao.clearAll(type.type)
                }
                if (loadKey == 0)
                    storageDao.writeInfo(Storage(type.type, "0"))
                newsDao.insertAll(data)
                storageDao.writeInfo(Storage(type.type, nextKey))
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaging)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

}