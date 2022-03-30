package activitytest.example.com.categories_module.bean

import com.google.gson.annotations.SerializedName

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