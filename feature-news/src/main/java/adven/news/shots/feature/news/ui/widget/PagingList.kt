package adven.news.shots.feature.news.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.Flow

@Composable
fun <Data : Any> PagingList(
    flow: Flow<PagingData<Data>>,
    modifier: Modifier = Modifier,
    noItemsCallBack: (Boolean) -> Unit = {},
    itemSpacing: Dp = 20.dp,
    minItemCount: Int = 0,
    scrollState: LazyListState = rememberLazyListState(),
    itemContent: @Composable LazyItemScope.(value: Data?) -> Unit
) {
    val lazyCallLogs = flow.collectAsLazyPagingItems()
    LazyColumn(
        state = scrollState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(items = lazyCallLogs, itemContent = itemContent)
        lazyCallLogs.loadState.apply {
            when {
                refresh is LoadState.Loading -> {
                    item {
                        noItemsCallBack(false)
                        Loader(modifier = Modifier.fillParentMaxSize())
                    }
                }
                refresh is LoadState.Error -> {
                    val error = (refresh as LoadState.Error).error
                    item {
                        ErrorView(
                            modifier = Modifier.fillParentMaxSize(),
                            errorMessage = error.message.orEmpty(),
                        ) {
                            lazyCallLogs.refresh()
                        }
                    }
                }
                append is LoadState.Loading -> {
                    item {
                        Loader(modifier = Modifier.fillMaxSize())
                    }
                }
                append is LoadState.Error -> {
                    item {
                        ErrorView(
                            modifier = Modifier.fillMaxWidth(),
                            errorMessage = "Unable to load more data"
                        ) {
                            lazyCallLogs.retry()
                        }
                    }
                }
                append is LoadState.NotLoading && append.endOfPaginationReached -> {
                    item {
                        noItemsCallBack(lazyCallLogs.itemCount <= minItemCount)
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorView(
    errorMessage: String = "Ops something went wrong",
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    text: String = "Please wait..."
) {
    Column(
        modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(strokeWidth = 1.dp, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, style = MaterialTheme.typography.labelSmall)
    }
}