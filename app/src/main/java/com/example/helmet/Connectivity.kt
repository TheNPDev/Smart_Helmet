package com.example.helmet

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class Connectivity {

    fun geturl(url_esp32: String?): String? {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url_esp32)
            .build()
        return try {
            val response: Response = client.newCall(request).execute()
            response.body()?.string()
        } catch (error: IOException) {
            error.toString()
        }
    }
}