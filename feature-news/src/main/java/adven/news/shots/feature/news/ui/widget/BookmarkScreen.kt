package adven.news.shots.feature.news.ui.widget

import adven.news.shots.core.ui.widgets.NewsCard
import adven.news.shots.feature.mymodel.ui.NewsListViewModel
import adven.news.shots.feature.news.ui.util.launchIntent
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
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    var hasNoItem by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Box(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        PagingList(flow = viewModel.bookmarks, noItemsCallBack = {
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
            Text(text = "You don't have any bookmark")
        }
    }
}

