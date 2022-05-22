package com.example.coinscreencap.data.remote

import com.example.coinscreencap.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
     override fun intercept(chain: Interceptor.Chain): Response {
          var request = chain.request()
          request = request.newBuilder().addHeader(Constants.KEY,Constants.API_KEY).build()
          return chain.proceed(request)
     }

}