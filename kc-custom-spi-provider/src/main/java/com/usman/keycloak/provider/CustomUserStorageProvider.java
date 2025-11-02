package com.usman.keycloak.provider;

import com.usman.keycloak.model.MyUserEntity;
import com.usman.keycloak.repository.EncryptUtil;
import com.usman.keycloak.repository.MyUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.jboss.logging.Logger;
import org.keycloak.common.constants.ServiceAccountConstants;
import org.keycloak.component.ComponentModel;
import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.credential.CredentialInputUpdater;
import org.keycloak.credential.CredentialModel;
import org.keycloak.models.*;
import org.keycloak.models.cache.CachedUserModel;
import org.keycloak.models.cache.OnUserCache;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.models.jpa.UserAdapter;
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
            //StorageId storageId = new StorageId(id);
            String persistenceId = StorageId.externalId(id); // extract 7107
            System.out.println("CustomUserStorageProvider persistenceId: " + persistenceId);
            //var myUser = em.find(MyUserEntity.class, persistenceId);
            var myUser =  userRepository.findByUserId(persistenceId);
            System.out.println("CustomUserStorageProvider myUser by id: " + myUser);

            //return null; // Or convert DB user -> UserModel
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
//            TypedQuery<MyUserEntity> query = em.createNamedQuery("getUserByUsername", MyUserEntity.class);
//            query.setParameter("username", username);
//            List<MyUserEntity> result = query.getResultList();

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
//            TypedQuery<MyUserEntity> query = em.createNamedQuery("getUserByEmail", MyUserEntity.class);
//            query.setParameter("email", email);
//            List<MyUserEntity> result = query.getResultList();

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
        //em.persist(entity);
        userRepository.persist(entity);
        logger.info("added user: " + username);
        return new CustomUserAdapter(session, realm, model, entity);
    }

    @Override
    public boolean removeUser(RealmModel realm, UserModel user) {
        System.out.println("CustomUserStorageProvider remove user by username: " + user.getUsername());
        String persistenceId = StorageId.externalId(user.getId());
        //MyUserEntity entity = em.find(MyUserEntity.class, persistenceId);
        MyUserEntity entity = userRepository.findByUserId(persistenceId);
        if (entity == null) {
            logger.info("could not find user to remove: " + entity);
            return false;
        }
        //em.remove(entity);
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
        //System.out.println("CustomUserStorageProvider credentialType :"+credentialType);
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
        //return supportsCredentialType(credentialType);
        //return true;
        return supportsCredentialType(credentialType) && getPassword(user) != null;
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput input) {
        try {
            //System.out.println("CustomUserStorageProvider isValid input.getType() :"+input.getType());
            if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) {
                return false;
            }
            System.out.println("CustomUserStorageProvider isValid input.getChallengeResponse :" + input.getChallengeResponse());
//            var myUser = userRepository.findByUsername(user.getUsername());
//            System.out.println("CustomUserStorageProvider isValid myUser :"+myUser);
//            System.out.printf("CustomUserStorageProvider isValid Returning user for username=%s, externalId=%s, provider=%s",
//                    myUser.getUsername(), myUser.getUserId(), model.getId());
//            //return myUser != null && myUser.getPassword().equals(input.getChallengeResponse());
//            if (myUser == null) return false;
//            boolean checkPassword = EncryptUtil.checkPassword(input.getChallengeResponse(), myUser.getPassword());
//            System.out.println("CustomUserStorageProvider isValid checkPassword :" + checkPassword);
//            return checkPassword;
            UserCredentialModel cred = (UserCredentialModel)input;
            System.out.println("CustomUserStorageProvider cred value :"+cred.getValue());
            String password = getPassword(user);
            System.out.println("CustomUserStorageProvider user password :"+password);
            boolean checkPassword = password != null && password.equals(cred.getValue());
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
//        Object count = em.createNamedQuery("getUserCount").getSingleResult();
        //return ((Number)count).intValue();
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

//        String search = params.get(UserModel.SEARCH);
//        TypedQuery<MyUserEntity> query = em.createNamedQuery("searchForUser", MyUserEntity.class);
//        String lower = search != null ? search.toLowerCase() : "";
//        query.setParameter("search", "%" + lower + "%");
//        if (firstResult != null) {
//            query.setFirstResult(firstResult);
//        }
//        if (maxResults != null) {
//            query.setMaxResults(maxResults);
//        }
//        return query.getResultStream().map(entity -> new CustomUserAdapter(session, realm, model, entity));
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realm, GroupModel group, Integer firstResult, Integer maxResults) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realm, String attrName, String attrValue) {
        return Stream.empty();
    }

//    @Override
//    public Stream<UserModel> searchForUserStream(RealmModel realm, String search, Integer firstResult, Integer maxResults) {
//        List<MyUserEntity> list = userRepository.searchByUsernameOrEmail(search, firstResult, maxResults);
//        System.out.println("CustomUserStorageProvider searchForUserStream list.size :" + list.size());
//        return list.stream().map(ent -> new CustomUserAdapter(session, realm, model, ent));
//    }

//    @Override
//    public int getUsersCount(RealmModel realm) {
//        return userRepository.countAll();
//    }


//    @Override
//    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer integer, Integer integer1) {
//        return Stream.empty();
//    }
//
//    @Override
//    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String s, String s1) {
//        return Stream.empty();
//    }


    /// /////////////
    //  Required by CredentialInputUpdater

//    @Override
//    public boolean updateCredential(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
//        return false;
//    }
//
//    @Override
//    public void disableCredentialType(RealmModel realmModel, UserModel userModel, String s) {
//
//    }
//
//    @Override
//    public Stream<String> getDisableableCredentialTypesStream(RealmModel realmModel, UserModel userModel) {
//        return Stream.empty();
//    }

}