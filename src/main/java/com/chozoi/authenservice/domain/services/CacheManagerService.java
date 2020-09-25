package com.chozoi.authenservice.domain.services;

import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.utils.caches.GenKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.chozoi.authenservice.domain.utils.caches.GenKey.PREFIX;


@Service
@Slf4j
public class CacheManagerService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate<String, TokenInfo> tokenInfoRedisTemplate;

    public static final Integer TIME_OUT = 15 * 60 * 3600;

    public TokenInfo getToken(String token) {
        try {
            String key = GenKey.genTokenKey(token);
            return tokenInfoRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public void setToken(String token, Integer userId,String name) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(userId);
        tokenInfo.setName(name);
        log.info(tokenInfo + "");
        tokenInfoRedisTemplate.opsForValue().set(
                GenKey.genTokenKey(token),
                tokenInfo,
                Duration.ofMillis(TIME_OUT));
    }

    public void deleteValue(String token) {
        redisTemplate.delete(PREFIX + ":token:" +token);

    }

}
