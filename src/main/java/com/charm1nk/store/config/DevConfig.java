package com.charm1nk.store.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Slf4j
@Profile("dev")
@Configuration
public class DevConfig {
    @Value("${postgresql.doker.image.name}")
    private String postgresqlImageName;

    @Bean
    public PostgreSQLContainer postgreSQLContainer() {
        final var container = new PostgreSQLContainer(postgresqlImageName);
        container.start();
        log.info("DB URL: {}", container.getJdbcUrl());
        log.info("DB USER: {}", container.getUsername());
        log.info("DB PASSWORD: {}", container.getPassword());

        return container;
    }

    @Bean
    @Primary
    public DataSource dataSource(PostgreSQLContainer oracleContainer) {
        final var hikariConfig = new HikariConfig();
        hikariConfig.setPassword(oracleContainer.getPassword());
        hikariConfig.setUsername(oracleContainer.getUsername());
        hikariConfig.setJdbcUrl(oracleContainer.getJdbcUrl());
        final var hikariDataSource = new HikariDataSource(hikariConfig);
        Flyway.configure()
                .dataSource(hikariDataSource)
                .locations("classpath:db", "classpath:testsql")
                .baselineVersion("0")
                .baselineOnMigrate(true)
                .schemas("public")
                .ignoreMissingMigrations(true)
                .outOfOrder(true)
                .placeholderReplacement(false)
                .load()
                .migrate();
        return hikariDataSource;
    }
}
