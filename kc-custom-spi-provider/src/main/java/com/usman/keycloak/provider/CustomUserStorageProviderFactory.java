package com.usman.keycloak.provider;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomUserStorageProviderFactory implements UserStorageProviderFactory<CustomUserStorageProvider> {

    public static final String PROVIDER_ID = "custom-user-provider";

    private static final List<ProviderConfigProperty> CONFIG_PROPERTIES = new ArrayList<>();

    static {
        ProviderConfigProperty priority = new ProviderConfigProperty();
        priority.setName("priority");
        priority.setLabel("Priority");
        priority.setHelpText("Order in which providers are queried. Lower values have higher priority.");
        priority.setType(ProviderConfigProperty.STRING_TYPE);
        CONFIG_PROPERTIES.add(priority);

        ProviderConfigProperty dbUrl = new ProviderConfigProperty();
        dbUrl.setName("dbUrl");
        dbUrl.setLabel("Database URL");
        dbUrl.setHelpText("Connection string to your user database.");
        dbUrl.setType(ProviderConfigProperty.STRING_TYPE);
        CONFIG_PROPERTIES.add(dbUrl);

        // Add more custom configs if needed
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return CONFIG_PROPERTIES;
    }


    @Override
    public CustomUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new CustomUserStorageProvider(session, model);
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "Custom User provider";
    }

    @Override
    public void close() {
        System.out.println("<<<<<< Closing provider factory >>>>>>");
    }

}
