package com.usman.keycloak.repository;


import com.usman.keycloak.model.MyUserEntity;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.sql.DataSource;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

    @Override
    public String getPersistenceUnitName() {
        return "myPersistenceUnit";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return HibernatePersistenceProvider.class.getName();
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        //return PersistenceUnitTransactionType.RESOURCE_LOCAL; // do not use this
        return PersistenceUnitTransactionType.JTA;
    }

    @Override
    public DataSource getJtaDataSource() {
        org.apache.commons.dbcp2.BasicDataSource ds = new org.apache.commons.dbcp2.BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/collegedb");
        ds.setUsername("college");
        ds.setPassword("college@001");
        return ds;
    }

    ///    This is for JTA
    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.transaction.coordinator_class", "jta");
        properties.setProperty("hibernate.current_session_context_class", "jta");
        properties.setProperty("hibernate.transaction.jta.platform",
                "org.hibernate.engine.transaction.jta.platform.internal.JBossStandAloneJtaPlatform");
        return properties;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        // Configure your DataSource here (e.g., HikariCP, BasicDataSource)
        // For simplicity, a basic setup is shown. In production, use a connection pool.
        org.apache.commons.dbcp2.BasicDataSource ds = new org.apache.commons.dbcp2.BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver"); // Or your database driver
        ds.setUrl("jdbc:postgresql://localhost:5432/collegedb");
        ds.setUsername("college");
        ds.setPassword("college@001");
        return ds;
    }

//    This is for RESOURCE_LOCAL (NonJTA)

//    @Override
//    public Properties getProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.setProperty("hibernate.hbm2ddl.auto", "none"); // Or validate, create-drop
//        properties.setProperty("hibernate.show_sql", "true");
//        return properties;
//    }

    @Override
    public List<String> getMappingFileNames() { return Collections.emptyList(); }

    @Override
    public List<URL> getJarFileUrls() { return Collections.emptyList(); }

    @Override
    public URL getPersistenceUnitRootUrl() { return null; }

    @Override
    public List<String> getManagedClassNames() {
        return List.of(MyUserEntity.class.getName()); // List your entity classes
    }

    @Override
    public boolean excludeUnlistedClasses() { return false; }

    @Override
    public SharedCacheMode getSharedCacheMode() { return SharedCacheMode.UNSPECIFIED; }

    @Override
    public ValidationMode getValidationMode() { return ValidationMode.AUTO; }



    @Override
    public String getPersistenceXMLSchemaVersion() { return "2.1"; }

    @Override
    public ClassLoader getClassLoader() { return Thread.currentThread().getContextClassLoader(); }

    @Override
    public void addTransformer(ClassTransformer transformer) { }

    @Override
    public ClassLoader getNewTempClassLoader() { return null; }

}