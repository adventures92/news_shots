package adven.news.shots.core.data.mapper

import adven.news.shots.core.data.model.NewsData
import adven.news.shots.core.database.model.NewsEntity
import javax.inject.Inject

class NewsEntityMapper @Inject constructor() : Mapper<NewsData, NewsEntity> {

    override fun invoke(src: NewsData): NewsEntity = with(src) {
        NewsEntity(
            uid = id,
            source = source,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            isBookmarked = isBookmarked,
            content = content,
            category = category
        )
    }

}