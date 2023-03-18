package adven.news.shots.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "news")
data class NewsEntity(
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    @ColumnInfo(index = true)
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
    val category: String,
    val isBookmarked : Boolean,
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
)