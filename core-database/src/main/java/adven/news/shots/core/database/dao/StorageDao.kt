package adven.news.shots.core.database.dao

import adven.news.shots.core.database.model.Storage
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface StorageDao {

    @Insert(onConflict = REPLACE)
    suspend fun writeInfo(storage: Storage): Long

    @Query("SELECT * FROM storage")
    fun readAllKey(): Flow<List<Storage>>

    @Query("DELETE FROM storage WHERE `key` = :key ")
    suspend fun clearStorage(key: String)

    @Query("SELECT * FROM storage WHERE `key` = :key")
    fun observeKey(key: String): Flow<Storage>

    @Query("SELECT * FROM storage WHERE `key` = :key")
    suspend fun readKey(key: String): Storage?

}