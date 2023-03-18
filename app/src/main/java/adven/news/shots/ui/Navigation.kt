package adven.news.shots.ui

import adven.news.shots.core.data.model.NewsCategories
import adven.news.shots.feature.mymodel.ui.CategoryNewsScreen
import adven.news.shots.feature.mymodel.ui.MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navigateToCategory = {
                navController.navigate("category/${it.name}")
            })
        }
        composable("category/{category}") {
            val category = it.arguments?.getString("category")
            if (category == null)
                navController.popBackStack()
            else {
                CategoryNewsScreen(
                    newsCategories = NewsCategories.valueOf(category),
                    onBackPress = {
                        navController.popBackStack()
                    })
            }
        }
    }
}
