package com.coderus.codingchallenge.data

data class CatResponseItem(
    val _id: String,
    val createdAt: String,
    val owner: String,
    val tags: List<String>,
    val updatedAt: String
){
    fun getTagsText(): String {
        return tags.joinToString(separator = "\n")
    }
}
