package com.webtoonmaker.api.chat.infra.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 단일 Redis 서버를 사용하는 설정-> Redis 서버 주소 지정
        config.useSingleServer().setAddress("redis://localhost:6379");
        // 클러스터 환경이라면 .useClusterServers()
//        config.useClusterServers()
//            .addNodeAddress("redis://node1:6379", "redis://node2:6379");

        return Redisson.create(config);
    }
}