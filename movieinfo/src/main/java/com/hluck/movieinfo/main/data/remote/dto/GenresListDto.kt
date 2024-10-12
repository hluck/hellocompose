package com.hluck.movieinfo.main.data.remote.dto

import com.hluck.movieinfo.main.domain.models.Genre

data class GenresListDto(
    val genres: List<Genre>
)
