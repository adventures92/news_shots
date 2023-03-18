package adven.news.shots.feature.mymodel.ui

import adven.news.shots.core.data.model.NewsCategories
import adven.news.shots.core.data.model.NewsType
import adven.news.shots.feature.news.ui.BottomNavigation
import adven.news.shots.feature.news.ui.widget.BookmarkScreen
import adven.news.shots.feature.news.ui.widget.CategoryScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigateToCategory: (NewsCategories) -> Unit,
    navHostController: NavHostController = rememberNavController(),
) {
    Scaffold(bottomBar = {
        BottomNavigation(navController = navHostController)
    }, topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "News Shots")
        })
    }) {
        NavHost(
            navController = navHostController,
            modifier = Modifier.padding(it),
            startDestination = "home"
        ) {
            composable("category") {
                CategoryScreen(onItemClick = navigateToCategory)
            }
            composable("home") {
                NewsListScreen(newsType = NewsType.Headline)
            }
            composable("bookmark") {
                BookmarkScreen()
            }
        }
    }
}