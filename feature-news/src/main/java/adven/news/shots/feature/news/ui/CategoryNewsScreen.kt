package adven.news.shots.feature.mymodel.ui

import adven.news.shots.core.data.model.NewsCategories
import adven.news.shots.core.data.model.NewsType
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryNewsScreen(
    newsCategories: NewsCategories,
    onBackPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = newsCategories.name.toString())
            }, navigationIcon = {
                IconButton(onClick = onBackPress) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                }
            })
        }
    ) {
        NewsListScreen(
            newsType = NewsType.Category(newsCategories),
            modifier = Modifier.padding(it)
        )
    }
}