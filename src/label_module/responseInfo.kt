package com.example.label_module

/**
 * @author peichendong
 * @Description TODO
 * @date 2021/5/25-13:30
 */
sealed class ResponseInfo {
    data class TagsNum(val code: Int?, val num: Int?, val message: String?)
    data class Tags(val code: Int?,val list: List<String>?,val message: String?)
}