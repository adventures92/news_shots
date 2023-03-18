package adven.news.shots.feature.news.ui.util

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.launchIntent(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        .apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    startActivity(intent)
}
