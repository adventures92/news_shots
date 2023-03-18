package adven.news.shots.core.data.model

sealed class NewsType(val type: String) {
    object Headline : NewsType("headline")
    data class Category(val category: NewsCategories) : NewsType(category.slug)
}
