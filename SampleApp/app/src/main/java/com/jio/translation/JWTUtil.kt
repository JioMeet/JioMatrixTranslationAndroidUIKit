package com.jio.translation

import android.util.Base64
import android.util.Log
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.io.UnsupportedEncodingException

object JWTUtil {

    var currentTimeInSeconds = System.currentTimeMillis() / 1000

    var expirationTime =
        currentTimeInSeconds + 30 * 24 * 60 * 60 // 30 days * 24 hours * 60 minutes * 60 seconds

    @Throws(Exception::class)
    fun decoded(jwtEncoded: String): String {
        return try {
            val split = jwtEncoded.split("\\.".toRegex()).toTypedArray()
            Log.e("JWT_DECODED", "Header: " + getJson(split[0]))
            Log.e("JWT_DECODED", "Body: " + getJson(split[1]))
            getJson(split[1])
        } catch (e: UnsupportedEncodingException) {
            ""
        }
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, charset("UTF-8"))
    }

    fun getJwtToken(mobileNum: String, secretKey: String): String? {
        return getJWTToken(
            secretKey,
            mobileNum
        )
    }

    fun getJWTToken(secretKey: String, mobileNum: String): String? {
        var token: String? = null
        try {
            token = Jwts.builder()
                .claim("platform", "jiomeet")
                .claim("phoneNo", mobileNum)
                .claim("source","myjio")
                .claim("subscriberId", mobileNum)
                .claim("iat", currentTimeInSeconds)
                .claim("exp", expirationTime)
                .signWith(SignatureAlgorithm.HS256, secretKey.toByteArray(charset("UTF-8")))
                .compact()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return token
    }
}
