package adven.news.shots.core.data.mapper

import adven.hiss.core.network.model.Article
import adven.news.shots.core.database.model.NewsEntity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ArticleMapper @Inject constructor() : Mapper<Article, NewsEntity> {

    override fun invoke(src: Article) = with(src) {
        NewsEntity(
            source = source?.name.orEmpty(),
            author = author.orEmpty(),
            title = title.orEmpty(),
            description = description.orEmpty(),
            url = url.orEmpty(),
            urlToImage = urlToImage.orEmpty(),
            publishedAt = try {
                SimpleDateFormat("yyyy-MM-ddTH:mm:ssZ", Locale.getDefault()).parse(publishedAt)
            } catch (e: Exception) {
                Date()
            },
            content = content.orEmpty(),
            isBookmarked = false,
            category = ""
        )
    }

}