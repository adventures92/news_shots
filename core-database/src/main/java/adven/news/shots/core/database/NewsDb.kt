package adven.news.shots.core.database

import adven.news.shots.core.database.dao.NewsDao
import adven.news.shots.core.database.dao.StorageDao
import adven.news.shots.core.database.model.NewsEntity
import adven.news.shots.core.database.model.Storage
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [NewsEntity::class, Storage::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsDb : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun storageDao(): StorageDao
}
