package com.minilink.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @Author: 徐志斌
 * @CreateTime: 2024-12-19  16:25
 * @Description: MinIO 配置类
 * @Version: 1.0
 */
public class MinioConfig {
    @Value("${storage.minio.endpoint}")
    private String minioEndpoint;
    @Value("${storage.minio.access-key}")
    private String minioAccessKey;
    @Value("${storage.minio.secret-key}")
    private String minioSecretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(minioAccessKey, minioSecretKey)
                .build();
    }
}
