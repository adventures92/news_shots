package adven.news.shots.core.data.model

import java.util.*

data class NewsData(
    val id: Int,
    val source: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val isBookmarked: Boolean,
    val content: String,
    val category : String,
)