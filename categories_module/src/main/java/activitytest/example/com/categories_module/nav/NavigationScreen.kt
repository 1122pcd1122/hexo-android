package activitytest.example.com.categories_module.nav

sealed class NavigationScreen {
    class CategoriesScreen(val title: String = "categories",val route: String = "categoriesScreen"):NavigationScreen()
    class ArticleScreen(val title: String = "article",val route: String = "articleScreen"):NavigationScreen()
}