package com.example.apicalltask.data

data class Data(
    val description: String,
    val genre_name: String,
    val is_all_subscribed: Int,
    val is_favourite: Int,
    val is_live: Int,
    val is_live_subscribed: Int,
    val is_premium: Int,
    val is_subscribed: Int,
    val poster_image: String,
    val published_on: String,
    val scheduledStartTime: String,
    val slug: String,
    val thumbnail_image: String,
    val title: String,
    val trailer_hls_url: Any,
    val trailer_status: Any,
    val video_category_name: String,
    val video_duration: String,
    val view_count: Int
)