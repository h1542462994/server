package org.tty.server.spider

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

data class NetResponse(val code:Int, val value:String){
    fun toDocument(): Document {
        return Jsoup.parse(value)
    }
    fun toMap(): JsonNode {
        return ObjectMapper().readTree(value)
    }
}