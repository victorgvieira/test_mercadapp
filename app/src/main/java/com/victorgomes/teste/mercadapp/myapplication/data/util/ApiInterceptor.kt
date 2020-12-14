package com.victorgomes.teste.mercadapp.myapplication.data.util

import okhttp3.Interceptor
import okhttp3.Response


class ApiInterceptor :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request().newBuilder().apply {
                header("Content-Type", "application/json")
                header("Accept", "application/json")
                method(request().method, request().body)
            }.build()
        )
    }
}