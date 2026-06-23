package com.example.muaythaitrainer.model

import java.io.Serializable

data class Technique(
    val id: Int,
    val name: String,
    val description: String,
    val videoId: String,
    val category: String,
    val imageResId: Int,        // для карточки (без q)
    val detailImageResId: Int   // для детального экрана (с q)
) : Serializable