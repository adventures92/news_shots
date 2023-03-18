package adven.news.shots.feature.mymodel.ui

import adven.news.shots.core.data.NewsRepository
import adven.news.shots.core.data.model.NewsData
import adven.news.shots.core.data.model.NewsType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getPagingList(country: String, newsType: NewsType): Flow<PagingData<NewsData>> {
        return newsRepository.fetchNews(newsType, country)
    }

    val bookmarks: Flow<PagingData<NewsData>> = newsRepository.fetchBookmarks()
        .cachedIn(viewModelScope)

    fun toggleBookmark(newsData: NewsData) = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.toggleBookMark(newsData)
    }

}
