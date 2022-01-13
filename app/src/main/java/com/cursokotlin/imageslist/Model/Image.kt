package com.cursokotlin.imageslist.Model

data class ImageRandom (
        val id: String,
        val description: String,
        val alt_description: String,
        var urls: Urls
    )

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)