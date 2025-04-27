package com.example.aiplannerapplication.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.math.log

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("###", "intercept: comig to this in custom interceptor")
        val req = chain.request()
        val res = chain.proceed(req)
        Log.d("###", "intercept: req method " + req.method)
        Log.d("###", "intercept: req method url " + req.url)

        return res
    }
}

class ErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("###", "intercept: comig to this in custom error interceptor")
        val req = chain.request()
        val res = chain.proceed(req)
        Log.d("###", "intercept: res code " + res.code)
        Log.d("###", "intercept: res message " + res.message)

        return res
    }
}