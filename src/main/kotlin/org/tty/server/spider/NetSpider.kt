package org.tty.server.spider

import org.apache.http.NameValuePair
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import java.util.*

class NetSpider(var domain: String) {
    private val userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36"

    private val httpClient = HttpClients.createDefault()
    private val requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build()

    fun get(uri: String): NetResponse {
        val request = HttpGet("${domain}${uri}")
        request.setHeader("User-Agent", userAgent)
        request.config = requestConfig
        val response = httpClient.execute(request)
        val value = EntityUtils.toString(response.entity, "GBK")
        return NetResponse(response.statusLine.statusCode, value)
    }

    fun post(uri: String, vararg params: Pair<String, Any?>): NetResponse {
        val request = HttpPost("${domain}${uri}")
        request.setHeader("User-Agent", userAgent)
        request.config = requestConfig
        request.entity = UrlEncodedFormEntity(
            params.map{  BasicNameValuePair(it.first, it.second.toString())
            }, Charsets.UTF_8)
        val response = httpClient.execute(request)
        val value = EntityUtils.toString(response.entity, "GBK")
        return NetResponse(response.statusLine.statusCode, value)
    }
}