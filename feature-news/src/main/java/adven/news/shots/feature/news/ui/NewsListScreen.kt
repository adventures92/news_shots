package adven.news.shots.feature.mymodel.ui

import adven.news.shots.core.data.model.NewsType
import adven.news.shots.core.ui.widgets.NewsCard
import adven.news.shots.feature.news.ui.util.launchIntent
import adven.news.shots.feature.news.ui.widget.PagingList
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsListScreen(
    newsType: NewsType,
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    var list by remember {
        mutableStateOf(viewModel.getPagingList("in", newsType))
    }
    var hasNoItem by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        PagingList(flow = list, noItemsCallBack = {
            hasNoItem = it
        }, itemSpacing = 8.dp) {
            it?.let {
                NewsCard(
                    imageUrl = it.urlToImage,
                    title = it.title,
                    description = it.description,
                    author = it.author,
                    source = it.source,
                    publishedAt = it.publishedAt,
                    isBookmarked = it.isBookmarked,
                    onBookmarkClick = {
                        viewModel.toggleBookmark(it)
                    }) {
                    context.launchIntent(it.url)
                }
            }
        }
        AnimatedVisibility(visible = hasNoItem, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Seems like nothing is here")
        }
    }
}

