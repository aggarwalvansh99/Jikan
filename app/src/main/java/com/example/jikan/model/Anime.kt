package com.example.jikan.model

data class Anime(
    val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: Image
)

data class Image(
    val jpg: ImageDetails
)

data class ImageDetails(
    val image_url: String
)
