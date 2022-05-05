package com.news.data

import com.news.data.articles.ArticlesDto
import com.news.data.sources.SourcesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines/sources?language=en")
    suspend fun sources(): Result<SourcesDto>

    @GET("everything")
    suspend fun articles(
        @Query("sources") source: String,
        @Query("page") page: Int
    ): Result<ArticlesDto>
}