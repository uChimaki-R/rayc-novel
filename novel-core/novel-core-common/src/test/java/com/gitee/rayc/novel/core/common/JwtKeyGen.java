package com.gitee.rayc.novel.core.common;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-30
 * @Description: 生成jwt加密secret
 * @Version: 1.0
 */
public class JwtKeyGen {
    public static void main(String[] args) {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        byte[] encoded = secretKey.getEncoded();
        String secret = Base64.getEncoder().encodeToString(encoded);
        System.out.println(secret);
    }
}
