package activitytest.example.com.log_module.project.bean


import com.google.gson.annotations.SerializedName


data class LogBean(
        @SerializedName("code")
    val code: Int = 0,
        @SerializedName("listArticleByYears")
    val listArticleByYears: List<ArticleByYears> = listOf(),
        @SerializedName("message")
    val message: String = ""
)

data class ArticleByYears(
        @SerializedName("listArticle")
    val listArticle: List<Article> = listOf(),
        @SerializedName("year")
    val year: Int = 0
)

data class Article(
    @SerializedName("day")
    val day: String = "",
    @SerializedName("href")
    val href: String = "",
    @SerializedName("month")
    val month: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("year")
    val year: String = ""
)