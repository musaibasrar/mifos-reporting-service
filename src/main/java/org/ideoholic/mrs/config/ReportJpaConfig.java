package org.ideoholic.mrs.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = "org.ideoholic.mrs.dao",
    entityManagerFactoryRef = "reportEntityManagerFactory",
    transactionManagerRef = "reportTransactionManager"
)
@EntityScan(basePackages = "org.ideoholic.mrs.model")
public class ReportJpaConfig {

    @Bean(name = "reportEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean reportEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("reportDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("org.ideoholic.mrs.model")
                .persistenceUnit("report")
                .build();
    }

    @Bean(name = "reportTransactionManager")
    public PlatformTransactionManager reportTransactionManager(
            @Qualifier("reportEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
