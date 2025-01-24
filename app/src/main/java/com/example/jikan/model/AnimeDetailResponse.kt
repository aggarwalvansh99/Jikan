package com.example.jikan.model

data class AnimeDetailResponse(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val genres: List<Genre>,
    val trailer: Trailer?,
    val images: Images,
    val cast: List<Cast>)
data class Genre(
    val name: String
)

data class Trailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?
)

data class Cast(
    val character: Character,
    val voice_actors: List<VoiceActor>
)

data class Character(
    val name: String
)
data class Images(
    val jpg: ImageDetailss
)

data class ImageDetailss(
    val image_url: String
)

data class VoiceActor(
    val name: String,
    val language: String
)