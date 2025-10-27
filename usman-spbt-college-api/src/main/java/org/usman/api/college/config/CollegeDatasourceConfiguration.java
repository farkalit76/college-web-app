package org.usman.api.college.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.usman.api.college.repository",
        entityManagerFactoryRef = "collegeEntityManagerFactory",
        transactionManagerRef = "collegeTransactionManager"
)
public class CollegeDatasourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.college")
    public DataSourceProperties collegeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.college.hikari")
    public DataSource collegeDataSource() {
        return collegeDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Primary
    public EntityManagerFactoryBuilder collegeEntityManagerFactoryBuilder(JpaProperties jpaProperties, ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), persistenceUnitManager.getIfAvailable());
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean collegeEntityManagerFactory(@Qualifier("collegeEntityManagerFactoryBuilder") EntityManagerFactoryBuilder collegeEntityManagerFactoryBuilder) {
        return collegeEntityManagerFactoryBuilder
                .dataSource(collegeDataSource())
                .packages("org.usman.api.college.repository")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager collegeTransactionManager(@Qualifier("collegeEntityManagerFactory")
                           LocalContainerEntityManagerFactoryBean collegeEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(collegeEntityManagerFactory.getObject()));
    }

    @Bean
    @Primary
    public JdbcTemplate collegeJdbcTemplate() {
        return new JdbcTemplate(collegeDataSource());
    }
}
