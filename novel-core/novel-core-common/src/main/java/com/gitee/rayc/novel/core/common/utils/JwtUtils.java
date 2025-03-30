package com.gitee.rayc.novel.core.common.utils;

import com.gitee.rayc.novel.core.common.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @Author: Ray-C
 * @CreateTime: 2025-03-28
 * @Description: JWT 工具类
 * @Version: 1.0
 */
@UtilityClass
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class JwtUtils {

    /**
     * 根据用户ID生成JWT
     *
     * @param uid       用户ID
     * @param systemKey 系统标识
     * @return JWT
     */
    public String generateToken(Long uid, String systemKey) {
        return Jwts.builder()
                .setHeaderParam(JwtProperties.SYSTEM_KEY_HEADER, systemKey)
                .setSubject(uid.toString())
                .signWith(Keys.hmacShaKeyFor(JwtProperties.SECRET.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    /**
     * 解析JWT返回用户ID
     *
     * @param token     JWT
     * @param systemKey 系统标识
     * @return 用户ID
     */
    public Long parseToken(String token, String systemKey) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(JwtProperties.SECRET.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            // OK, we can trust this JWT
            // 判断该 JWT 是否属于指定系统
            if (Objects.equals(claimsJws.getHeader().get(JwtProperties.SYSTEM_KEY_HEADER), systemKey)) {
                return Long.parseLong(claimsJws.getBody().getSubject());
            }
        } catch (JwtException e) {
            log.warn("JWT解析失败:{}", token);
            // don't trust the JWT!
        }
        return null;
    }

}
