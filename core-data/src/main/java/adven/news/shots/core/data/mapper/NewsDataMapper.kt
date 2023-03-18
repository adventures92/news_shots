package adven.news.shots.core.data.mapper

import adven.news.shots.core.data.model.NewsData
import adven.news.shots.core.database.model.NewsEntity
import javax.inject.Inject

class NewsDataMapper @Inject constructor() : Mapper<NewsEntity,NewsData>{

    override fun invoke(src: NewsEntity): NewsData = with(src){
        NewsData(
            id = uid,
            source = source,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            isBookmarked = isBookmarked,
            publishedAt = publishedAt,
            content = content,
            category = category
        )
    }

}