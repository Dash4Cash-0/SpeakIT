package com.example.speakit.model

data class SentenceModel(
    val id: Long,
    val sourceText: String,
    val targetText: String,
    val forms: List<String> = emptyList(),
    val category: String = "All",
    val saved: Boolean = false

)