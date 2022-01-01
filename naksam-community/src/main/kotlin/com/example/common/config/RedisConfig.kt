package com.example.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories


@Configuration
@ConfigurationProperties("spring.redis")
class RedisProperties(
    var host: String = "",
    var port: Int = 0
)

@Configuration
@EnableRedisRepositories
class RedisRepositoryConfig(
    private val redisProperties: RedisProperties
) {

    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(redisProperties.host, redisProperties.port)

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val redisTemplate: RedisTemplate<ByteArray, ByteArray> = RedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}
