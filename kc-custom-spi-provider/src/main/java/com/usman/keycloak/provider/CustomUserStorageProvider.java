package com.usman.keycloak.provider;

import com.usman.keycloak.model.MyUserEntity;
import com.usman.keycloak.repository.EncryptUtil;
import com.usman.keycloak.repository.MyUserRepository;
import org.jboss.logging.Logger;
import org.keycloak.common.constants.ServiceAccountConstants;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInputUpdater;
import org.keycloak.models.*;
import org.keycloak.models.cache.CachedUserModel;
import org.keycloak.models.cache.OnUserCache;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.storage.user.UserQueryProvider;
import org.keycloak.storage.user.UserRegistrationProvider;

import java.util.*;
import java.util.stream.Stream;

public class CustomUserStorageProvider implements UserStorageProvider,
        UserLookupProvider,
        UserRegistrationProvider,
        UserQueryProvider,
        CredentialInputValidator,
        CredentialInputUpdater,
        OnUserCache {

    //protected EntityManager em;
    private final KeycloakSession session;
    private final ComponentModel model;
    private final MyUserRepository userRepository; // your DB repo

    private static final Logger logger = Logger.getLogger(CustomUserStorageProvider.class);
    public static final String PASSWORD_CACHE_KEY = CustomUserAdapter.class.getName() + ".password";

    public CustomUserStorageProvider(KeycloakSession session, ComponentModel model) {
        this.session = session;
        this.model = model;
        this.userRepository = new MyUserRepository();
        //em = session.getProvider(JpaConnectionProvider.class, "user-store").getEntityManager();
        System.out.println("userRepository created as :"+userRepository);
    }

    @Override
    public void preRemove(RealmModel realm) {
    }

    @Override
    public void preRemove(RealmModel realm, GroupModel group) {
    }

    @Override
    public void preRemove(RealmModel realm, RoleModel role) {
    }

    @Override
    public void close() {
        System.out.println("CustomUserStorageProvider close called." );
        // Cleanup resources if needed
    }

    // 👇 You can access your config value here:
    private int getPriority() {
        System.out.println("CustomUserStorageProvider getPriority called and setting priority." );
        String value = model.get("priority");
        return value != null ? Integer.parseInt(value) : 0;
    }


    //  Required by UserLookupProvider
    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        try {
            System.out.println("CustomUserStorageProvider fetching user by ID: " + id);
            if (id != null && id.startsWith(ServiceAccountConstants.SERVICE_ACCOUNT_USER_PREFIX)) {
                return null;
            }
            String persistenceId = StorageId.externalId(id); // extract 7107
            System.out.println("CustomUserStorageProvider persistenceId: " + persistenceId);
            var myUser =  userRepository.findByUserId(persistenceId);
            System.out.println("CustomUserStorageProvider myUser by id: " + myUser);

            if (myUser == null) {
                logger.info("could not find user by id: " + id);
                return null;
            }
            CustomUserAdapter adapter = new CustomUserAdapter(session, realm, model, myUser);
            System.out.printf("CustomUserStorageProvider getUserById Returning user for username=%s, externalId=%s, provider=%s",
                    myUser.getUsername(), myUser.getId(), model.getId());
            System.out.println("-");
            return  adapter;
        } catch (Exception e) {
            System.err.println("CustomUserStorageProvider *** getUserById error :"+ e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        try{
            System.out.println("CustomUserStorageProvider fetching user by username: " + username);
            if (username != null && username.startsWith(ServiceAccountConstants.SERVICE_ACCOUNT_USER_PREFIX)) {
                return null;
            }
            var myUser =  userRepository.findByUsername(username);
            System.out.println("CustomUserStorageProvider myUser by username: " + myUser);
            if (myUser == null){
                logger.info("could not find username: " + username);
                return null;
            }
            CustomUserAdapter adapter = new CustomUserAdapter(session, realm, model, myUser);
            System.out.printf("CustomUserStorageProvider getUserByUsername Returning user for username="+ myUser);
            System.out.println("-");
            return adapter;
        } catch (Exception e) {
            System.err.println("CustomUserStorageProvider *** getUserByUsername error :"+ e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        try {
            System.out.println("CustomUserStorageProvider Fetching user by email: " + email);
            var myUser =  userRepository.findByEmail(email);
            if (myUser == null){
                logger.info("could not find email: " + email);
                return null;
            }
            CustomUserAdapter adapter = new CustomUserAdapter(session, realm, model, myUser);
            System.out.printf("CustomUserStorageProvider getUserByEmail Returning user by email ="+myUser);
            System.out.println("-");
            return adapter;
        }catch (Exception e) {
            System.err.println("CustomUserStorageProvider *** getUserByEmail error :"+ e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel addUser(RealmModel realm, String username) {
        System.out.println("CustomUserStorageProvider add user by username: " + username);
        MyUserEntity entity = new MyUserEntity();
        entity.setId(getUserId());
        entity.setUsername(username);
        userRepository.persist(entity);
        logger.info("added user: " + username);
        return new CustomUserAdapter(session, realm, model, entity);
    }

    @Override
    public boolean removeUser(RealmModel realm, UserModel user) {
        System.out.println("CustomUserStorageProvider remove user by username: " + user.getUsername());
        String persistenceId = StorageId.externalId(user.getId());
        MyUserEntity entity = userRepository.findByUserId(persistenceId);
        if (entity == null) {
            logger.info("could not find user to remove: " + entity);
            return false;
        }
        userRepository.remove(entity);
        logger.info("removed user: " + user.getUsername());
        return true;
    }

    @Override
    public void onCache(RealmModel realm, CachedUserModel user, UserModel delegate) {
        String password = ((CustomUserAdapter)delegate).getPassword();
        if (password != null) {
            user.getCachedWith().put(PASSWORD_CACHE_KEY, password);
        }
    }


    private String getUserId(){
        Random random = new Random();
        return "U"+random.nextInt(99000) + 1000L;//start from 1000
    }

    //  Required by CredentialInputValidator
    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean updateCredential(RealmModel realm, UserModel user, CredentialInput input) {
        if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) return false;
        UserCredentialModel cred = (UserCredentialModel)input;
        CustomUserAdapter adapter = getUserAdapter(user);
        adapter.setPassword(cred.getValue());

        return true;
    }

    public CustomUserAdapter getUserAdapter(UserModel user) {
        if (user instanceof CachedUserModel) {
            return (CustomUserAdapter)((CachedUserModel) user).getDelegateForUpdate();
        } else {
            return (CustomUserAdapter) user;
        }
    }


    @Override
    public void disableCredentialType(RealmModel realm, UserModel user, String credentialType) {
        if (!supportsCredentialType(credentialType)) return;
        getUserAdapter(user).setPassword(null);
    }

    @Override
    public Stream<String> getDisableableCredentialTypesStream(RealmModel realm, UserModel user) {
        if (getUserAdapter(user).getPassword() != null) {
            Set<String> set = new HashSet<>();
            set.add(PasswordCredentialModel.TYPE);
            return set.stream();
        } else {
            return Stream.empty();
        }
    }


    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        System.out.println("CustomUserStorageProvider isConfiguredFor :"+supportsCredentialType(credentialType));
        return supportsCredentialType(credentialType) && getPassword(user) != null;
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput input) {
        try {
            if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) {
                return false;
            }
            System.out.println("CustomUserStorageProvider isValid input.getChallengeResponse :" + input.getChallengeResponse());
            UserCredentialModel cred = (UserCredentialModel)input;//coming from input
            System.out.println("CustomUserStorageProvider cred value :"+cred.getValue());
            String password = getPassword(user);//coming from backend
            System.out.println("CustomUserStorageProvider user password :"+password);
            boolean checkPassword = password != null && EncryptUtil.checkPassword(cred.getValue(), password);
            System.out.println("CustomUserStorageProvider isValid checkPassword :" + checkPassword);
            return checkPassword;

        } catch (Throwable e) {
            System.err.println("CustomUserStorageProvider *** isValid error :"+ e.getMessage());
            return false;
        }
    }

    public String getPassword(UserModel user) {
        String password = null;
        if (user instanceof CachedUserModel) {
            password = (String)((CachedUserModel)user).getCachedWith().get(PASSWORD_CACHE_KEY);
        } else if (user instanceof CustomUserAdapter) {
            password = ((CustomUserAdapter)user).getPassword();
        }
        return password;
    }

    @Override
    public int getUsersCount(RealmModel realm) {
        return userRepository.countAll();
    }

    //From UserQueryProvider - to  display the local users in Keycloak UI

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, Map<String, String> params, Integer firstResult, Integer maxResults) {
        String search = params != null ? params.getOrDefault("keycloak.session.realm.users.query.search", "") : "";
        List<MyUserEntity> entities;
        if (search == null || search.isBlank()) {
            entities = userRepository.findAll(firstResult, maxResults);
        } else {
            entities = userRepository.searchByUsernameOrEmail(search, firstResult, maxResults);
        }
        System.out.println("CustomUserStorageProvider searchForUserStream entities.size :" + entities.size());
        return entities.stream().map(ent -> new CustomUserAdapter(session, realm, model, ent));
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realm, GroupModel group, Integer firstResult, Integer maxResults) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realm, String attrName, String attrValue) {
        return Stream.empty();
    }

}