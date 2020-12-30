package org.tty.server.service

import org.apache.tomcat.websocket.TransformationFactory
import org.springframework.stereotype.Component
import org.tty.server.spider.NetSpider
import org.tty.server.common.*
import java.math.BigInteger
import java.security.KeyFactory
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
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
        map["csrf_token"] = document.getElementById("csrftoken").attr("value")
        map["action"] = "${document.select("body > div.container.container_1170 > div.row.sl_log_bor4 > div.col-sm-4.sl_log_rt > form").attr("action")}?time=${timeStamp}"
        map["yzmPic"] = document.getElementById("yzmPic").attr("src")
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
        map["encrypted"] = cipher.doFinal("123456".bytes).b64encode

        //println(document)
        return map
    }
}