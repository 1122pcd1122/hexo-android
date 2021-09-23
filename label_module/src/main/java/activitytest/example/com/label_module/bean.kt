package activitytest.example.com.label_module

import com.google.gson.annotations.SerializedName

data class LabelsBean(
        @SerializedName("code")
        val code: Int = 0,
        @SerializedName("list")
        val list: List<String> = listOf(),
        @SerializedName("message")
        val message: String = ""
)

data class Articles(
        @SerializedName("code")
    val code: Int = 0,
        @SerializedName("listArticle")
    val listArticle: List<Article> = listOf(),
        @SerializedName("message")
    val message: String = ""
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