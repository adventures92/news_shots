package adven.news.shots.core.ui.widgets

import adven.news.shots.core.ui.MyApplicationTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    imageUrl: String,
    title: String,
    description: String,
    author: String,
    source: String,
    publishedAt: Date,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onCardClick: () -> Unit
) {
    val date = SimpleDateFormat("dd-MMM-yyyy h:mm a", Locale.getDefault())
    Card(onClick = onCardClick) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(16 / 9f)
                    .background(Color.Gray)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Published At : ${date.format(publishedAt)}",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(text = "By - $author", style = MaterialTheme.typography.labelSmall)
                    }
                    IconButton(onClick = onBookmarkClick) {
                        Icon(
                            if (isBookmarked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                Text(text = title, style = MaterialTheme.typography.bodyLarge)
                Text(text = description, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = source,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .border(
                            1.dp,
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.secondary
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)

                )
            }
            Text(
                text = "Read full story",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNewsCard() {
    MyApplicationTheme {
        NewsCard(
            imageUrl = "",
            title = "Oppo Find N2 Flip goes on sale in India: Price, offers and more - Times of India",
            description = "Oppo launched its first foldable smartphone -- Oppo Find N2 Flip in India earlier this week. The smartphone is now up for sale in the country. The cus",
            author = "TIMESOFINDIA.COM",
            source = "Times Of India",
            isBookmarked = true,
            publishedAt = Date(),
            onBookmarkClick = {}
        ) {

        }
    }
}
