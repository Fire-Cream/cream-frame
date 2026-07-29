package com.cream.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis测试类
 *
 * @author Cream
 * @since 2026-07-11 20:46
 */
@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public void redis() {
        redisTemplate.opsForValue().set("key", "value");
    }

}
