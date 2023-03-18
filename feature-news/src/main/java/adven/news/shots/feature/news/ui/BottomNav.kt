package adven.news.shots.feature.news.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavHostController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar(
    ) {
        bottomItems.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(it.route) },
                icon = {
                    Icon(it.icon, contentDescription = null)
                }, label = {
                    Text(it.name)
                })
        }
    }

}

data class BottomBarItem(val name: String, val route: String, val icon: ImageVector)

private val bottomItems = listOf(
    BottomBarItem(
        name = "Category",
        route = "category",
        icon = Icons.Rounded.Menu
    ),
    BottomBarItem(
        name = "Home",
        route = "home",
        icon = Icons.Rounded.Home
    ),
    BottomBarItem(
        name = "Bookmarks",
        route = "bookmark",
        icon = Icons.Rounded.Favorite
    )
)