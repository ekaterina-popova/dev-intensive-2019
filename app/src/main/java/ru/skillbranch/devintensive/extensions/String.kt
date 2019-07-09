package ru.skillbranch.devintensive.extensions

fun String.truncate(numberOfCharacters: Int = 16): String {
    val ending = "..."
    val result: String
    result = if (this.length >= numberOfCharacters) {
        val head = this.substring(0, numberOfCharacters)
        val tail = this.substring(numberOfCharacters - 1)
        val trimTail = tail.trim()
        if (trimTail.isNotEmpty()) head.trim() + ending else head.trim()
    } else {
        this.trim()
    }
    return result
}

fun String.stripHtml(): String {
    val result: String
    return ""
}