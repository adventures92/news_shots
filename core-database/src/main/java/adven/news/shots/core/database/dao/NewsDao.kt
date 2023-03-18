package adven.news.shots.core.database.dao

import adven.news.shots.core.database.model.NewsEntity
import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<NewsEntity>)

    @Query("SELECT * FROM news WHERE category LIKE :category")
    fun pagingSource(category: String): PagingSource<Int, NewsEntity>

    @Query("SELECT * FROM news WHERE uid = :id ORDER BY publishedAt DESC")
    suspend fun selectNews(id: Int): NewsEntity?

    @Query("DELETE FROM news WHERE category LIKE :category AND isBookmarked = false")
    suspend fun clearAll(category: String)

    @Update
    suspend fun updateNews(news: NewsEntity): Int

    @Query("SELECT * FROM news WHERE isBookmarked = true")
    fun pagingBookmark(): PagingSource<Int, NewsEntity>

}