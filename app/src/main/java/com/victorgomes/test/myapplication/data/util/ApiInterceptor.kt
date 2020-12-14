package com.victorgomes.test.myapplication.data.util

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*


const val API_KEY_PARAM = "apikey"
const val HASH_PARAM = "hash"
const val TS_PARAM = "ts"
const val API_KEY_VALUE = "6065a6562fa0d9c9241c59ba1bf27210"
const val API_PRIVATE_KEY_VALUE = "d8317b4b300c98db4ad761d7fb8b93d9421bb40a"

class ApiInterceptor :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request().newBuilder().apply {
                header("Content-Type", "application/json")
                header("Accept", "application/json")
                method(request().method, request().body)
                url(request().url.newBuilder().apply {
                    val ts = Date().time.toString()
//                    hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
                    val md5Content = ts + API_PRIVATE_KEY_VALUE + API_KEY_VALUE
                    addQueryParameter(API_KEY_PARAM, API_KEY_VALUE)
                    addQueryParameter(HASH_PARAM, md5(md5Content))
                    addQueryParameter(TS_PARAM, ts)
                }.build())
            }.build()
        )
    }
}

private fun md5(s: String): String? {
    val md5 = "MD5"
    try {
        // Create MD5 Hash
        val digest: MessageDigest = MessageDigest
            .getInstance(md5)
        digest.update(s.toByteArray())
        val messageDigest: ByteArray = digest.digest()

        // Create Hex String
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}