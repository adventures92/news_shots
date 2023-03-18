package adven.news.shots.feature.news.ui.widget

import adven.news.shots.core.data.model.NewsCategories
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CategoryScreen(onItemClick: (category: NewsCategories) -> Unit) {
    LazyVerticalGrid(modifier = Modifier.padding(8.dp), columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), content = {
            NewsCategories.values().forEach {
                item {
                    CategoryCard(
                        title = it.name,
                        color = MaterialTheme.colorScheme.secondary,
                        onItemClick = {
                            onItemClick(it)
                        }
                    )
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(title: String, color: Color, onItemClick: () -> Unit) {
    Card(
        onClick = onItemClick, modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, color)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}