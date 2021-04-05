package com.nikbrik.newsbyviewpager

enum class ArticleTag {
    IT, GLOBAL, SPORT;

    companion object {
        fun getStringArray(): Array<String> {
            val strings = mutableListOf<String>()
            values().forEach { tag ->
                strings.add(tag.toString())
            }
            return strings.toTypedArray()
        }
    }
}
