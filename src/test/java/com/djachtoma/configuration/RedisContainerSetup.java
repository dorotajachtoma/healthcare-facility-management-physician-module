package com.djachtoma.configuration;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisContainerSetup {

    private static final String DOCKER_IMAGE = "redis:5.0.3-alpine";

    public void start() {
        GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse(DOCKER_IMAGE));
        redisContainer.start();

        System.setProperty("healthcare.facility.management.database.host", redisContainer.getContainerIpAddress());
        System.setProperty("healthcare.facility.management.database.port", redisContainer.getFirstMappedPort().toString());
    }
}
