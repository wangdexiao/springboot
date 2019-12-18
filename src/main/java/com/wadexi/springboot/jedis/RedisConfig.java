package com.wadexi.springboot.jedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {

    private RedisConnectionFactory redisConnectionFactory;

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;



    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(30);//最大空闲数
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        jedisPoolConfig.setMaxWaitMillis(2000);//最大等待毫秒数
        //表示连接池在创建链接的时候会先测试一下链接是否可用，这样可以保证连接池中的链接都可用的。
        jedisPoolConfig.setTestOnBorrow(true);


        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        //获取单机的Redis配置
        RedisStandaloneConfiguration configuration = jedisConnectionFactory.getStandaloneConfiguration();
        configuration.setHostName(hostName);
//        configuration.setPassword(RedisPassword.of(password));
        configuration.setPort(port);

        this.redisConnectionFactory = jedisConnectionFactory;
        return jedisConnectionFactory;
    }


    @Bean
    public RedisTemplate restTemplate(){
        RedisTemplate restTemplate = new RedisTemplate();
        restTemplate.setConnectionFactory(redisConnectionFactory());
        RedisSerializer stringSerializer = restTemplate.getStringSerializer();
        restTemplate.setKeySerializer(stringSerializer);
        restTemplate.setValueSerializer(stringSerializer);
        restTemplate.setHashKeySerializer(stringSerializer);
        restTemplate.setHashValueSerializer(stringSerializer);
        return restTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate restTemplate = new StringRedisTemplate();
        restTemplate.setConnectionFactory(redisConnectionFactory());
        RedisSerializer stringSerializer = restTemplate.getStringSerializer();
        restTemplate.setKeySerializer(stringSerializer);
        restTemplate.setValueSerializer(stringSerializer);
        restTemplate.setHashKeySerializer(stringSerializer);
        restTemplate.setHashValueSerializer(stringSerializer);
        return restTemplate;
    }
}
