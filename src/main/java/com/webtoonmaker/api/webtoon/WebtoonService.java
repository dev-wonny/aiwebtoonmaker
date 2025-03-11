package com.webtoonmaker.api.webtoon;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * docker run --name redis -d -p 6379:6379 redis
 * curl -X POST "http://localhost:8080/webtoon/generate?projectId=123"
 * Redis에서 확인 >>> redis-cli >> KEYS *
 */
public class WebtoonService {
    private final RedissonClient redissonClient;

    public WebtoonService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void generateWebtoon(String projectId) {
        RLock lock = redissonClient.getLock("webtoon_processing_lock:" + projectId);

        try {
            // 락 획득 시도 (최대 5초 대기, 30초 후 자동 해제)
            boolean available = lock.tryLock(5, 30, TimeUnit.SECONDS);

            if (!available) {
                throw new RuntimeException("이미 실행 중입니다.");
            }

            // 웹툰 생성 실행
            System.out.println("웹툰 생성 시작: " + projectId);
            Thread.sleep(10000); // 웹툰 생성 로직 (10초 대기)

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 락 해제
            lock.unlock();
            System.out.println("웹툰 생성 완료: " + projectId);
        }
    }

}
