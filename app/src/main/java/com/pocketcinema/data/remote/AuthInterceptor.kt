package com.pocketcinema.data.remote

import com.pocketcinema.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_BEARER_TOKEN}")
            .addHeader("Accept", "application/json")
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}