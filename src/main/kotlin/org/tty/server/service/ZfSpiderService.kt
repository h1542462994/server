package org.tty.server.service

import org.jsoup.nodes.Document
import org.springframework.stereotype.Component
import org.tty.server.common.b64decode
import org.tty.server.common.b64encode
import org.tty.server.common.bytes
import org.tty.server.spider.NetSpider
import java.math.BigInteger
import java.security.KeyFactory
import java.security.spec.RSAPublicKeySpec
import java.util.*
import javax.crypto.Cipher
import kotlin.collections.LinkedHashMap

@Component
class ZfSpiderService {
    val netSpider = NetSpider("http://www.gdjw.zjut.edu.cn/jwglxt/xtgl/");

    fun link(): Map<String, Any> {
        val document = netSpider.get("login_slogin.html").toDocument()
        val map = LinkedHashMap<String, Any>()
        val timeStamp = Date().time
        map["timeStamp"] = timeStamp
        map["csrf_token"] = document.getElementById("csrftoken").attr("value")
        map["action"] = "${document.select("body > div.container.container_1170 > div.row.sl_log_bor4 > div.col-sm-4.sl_log_rt > form").attr("action")}?time=${timeStamp}"
        map["yzmPic"] = "/jwglxt/kaptcha?time=${timeStamp}"
        val rsaKey = netSpider.post("login_getPublicKey.html?time=${timeStamp}").toMap()
        val modulus = rsaKey["modulus"].textValue().bytes.b64decode
        val exponent = rsaKey["exponent"].textValue().bytes.b64decode
        map["modulus"] = modulus
        map["exponent"] = exponent
        val keySpec = RSAPublicKeySpec(BigInteger(modulus), BigInteger(exponent))
        val key = KeyFactory.getInstance("RSA").generatePublic(keySpec)
        map["key"] = key.toString()
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encrypted = cipher.doFinal("123456".bytes).b64encode
        map["username"] = "201806061201"
        map["encrypted"] = encrypted

        //println(document)
        return map
    }

    fun login(map: Map<String, Any>, yzm: String) : Document {
        val result = netSpider.post("login_slogin.html?time=${map["timeStamp"]}",
            "csrftoken" to map["csrftoken"],
            "language" to "zh_CN",
            "yhm" to map["username"],
            "mm" to map["encrypted"],
            "mm" to map["encrypted"],
            "yzm" to yzm
        )
        return result.toDocument()
    }
}