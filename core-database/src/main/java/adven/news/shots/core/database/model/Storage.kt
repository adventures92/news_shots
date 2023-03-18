package adven.news.shots.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["key"], unique = true)]
)
data class Storage(
    @ColumnInfo(name = "key")
    val key: String,
    val value: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)