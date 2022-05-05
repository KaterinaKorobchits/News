package com.news.di.rest

import com.news.BuildConfig
import com.news.data.NewsService
import com.news.data.retrofit.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class RestModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideNewsService(): NewsService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_REST_URL)
                .client(okHttClient())
                .addCallAdapterFactory(ResultCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NewsService::class.java)
        }

        private fun okHttClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .addInterceptor(interceptor())

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor().apply {
                    level =
                        HttpLoggingInterceptor.Level.BODY
                }
                builder.addInterceptor(logging)
            }

            return builder.build()
        }

        private fun interceptor(): Interceptor {
            return Interceptor {
                val original = it.request()
                val url = original.url.newBuilder()
                    .addQueryParameter("apiKey", BuildConfig.NEWS_KEY)

                val request = original.newBuilder()
                    .url(url.build())
                    .build()
                return@Interceptor it.proceed(request)
            }
        }
    }
}