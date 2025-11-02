package com.usman.keycloak.provider;

import com.usman.keycloak.model.MyUserEntity;
import jakarta.ws.rs.core.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.*;
import org.keycloak.storage.ReadOnlyException;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

import java.util.*;
import java.util.stream.Stream;

public class CustomUserAdapter extends AbstractUserAdapterFederatedStorage {

    //private final KeycloakSession session;
    //private final RealmModel realm;
    private final ComponentModel model;
    private final MyUserEntity userEntity; // your POJO
    private final String keycloakId;

    public CustomUserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, MyUserEntity userEntity) {
        super(session, realm, model);
        //this.session = session;
        //this.realm = realm;
        this.model = model;
        this.userEntity = userEntity;
        System.out.printf("CustomUserAdapter constructor userEntity : "+this.userEntity);
        //this.keycloakId = StorageId.keycloakId(model, String.valueOf(userEntity.getUserId()));
        //this.keycloakId = model.getId() + ":" + userEntity.getUserId();;
       /* System.out.printf("CustomUserAdapter constructor model.getId() + \":\" + userEntity.getUserId():"+(model.getId() + ":" + userEntity.getId()));
        System.out.println("\n");
        System.out.printf("CustomUserAdapter constructor userEntity.getUserId():"+userEntity.getId());
        System.out.println("\n");
        System.out.printf("CustomUserAdapter constructor StorageId.keycloakId(model, String.valueOf(userEntity.getUserId())):"+StorageId.keycloakId(model, String.valueOf(userEntity.getId())));
        System.out.println("\n");
        System.out.printf("CustomUserAdapter constructor new StorageId(model.getId(), String.valueOf(userEntity.getUserId())).getId():"+new StorageId(model.getId(), String.valueOf(userEntity.getId())).getId());
        */
        this.keycloakId= StorageId.keycloakId(model, String.valueOf(userEntity.getId()));
        System.out.println("\n");
        System.out.printf("CustomUserAdapter constructor - created with keycloakId=%s, username=%s",this.keycloakId, userEntity.getUsername());
        System.out.println("\n");
    }

    public String getPassword() {
        return userEntity.getPassword();
    }

    public void setPassword(String password) {
        userEntity.setPassword(password);
    }


    @Override
    public String getId() {
        // VERY IMPORTANT: produce a federated id so Keycloak won't try to load local JPA UserEntity

//        System.out.println("CustomUserAdapter getId Returning user ID: (provider) :"+ storageProviderModel.getId());
//        System.out.println("CustomUserAdapter getId Realm in adapter: "+ realm.getName());
        //return storageProviderModel.getId() + ":" + userEntity.getUserId();
        //System.out.println("CustomUserProvider getId is called:"+this.keycloakId);
        return this.keycloakId;
    }

    @Override
    public String getUsername() {
//        System.out.println("CustomUserAdapter getUsername Returning user ID: (provider) :"+ storageProviderModel.getId());
//        System.out.println("CustomUserAdapter getUsername Realm in adapter: "+ realm.getName());
        //return userEntity.getUsername();
        System.out.println("CustomUserProvider getUsername is called:"+userEntity.getUsername());
        return userEntity.getUsername() != null ? userEntity.getUsername() : "";
    }
    @Override
    public void setUsername(String username) {
        throw new ReadOnlyException("Username cannot be changed for federated users:"+username);
    }

    @Override
    public String getEmail() {
        //return userEntity.getEmail();
        return userEntity.getEmail() != null ? userEntity.getEmail() : "";
    }

    @Override
    public void setEmail(String s) {

    }

    @Override
    public boolean isEmailVerified() {
        return true;
    } // or return actual DB flag

    @Override
    public void setEmailVerified(boolean b) {

    }

    @Override
    public Stream<GroupModel> getGroupsStream() {
        return Stream.empty();
    }

    @Override
    public void joinGroup(GroupModel groupModel) {

    }

    @Override
    public void leaveGroup(GroupModel groupModel) {

    }

    @Override
    public boolean isMemberOf(GroupModel groupModel) {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public void setSingleAttribute(String name, String value) {
        System.out.println("CustomUserProvider void setSingleAttribute called....:"+name);
        if (name.equals("phone")) {
            userEntity.setPhone(value);
        } else {
            super.setSingleAttribute(name, value);
        }
    }

    @Override
    public void removeAttribute(String name) {
        System.out.println("CustomUserProvider void removeAttribute called....:"+name);
        if (name.equals("phone")) {
            userEntity.setPhone(null);
        } else {
            super.removeAttribute(name);
        }
    }

    @Override
    public void setAttribute(String name, List<String> values) {
        System.out.println("CustomUserProvider void setAttribute called....:"+values);
        if (name.equals("phone")) {
            userEntity.setPhone(values.get(0));
        } else {
            super.setAttribute(name, values);
        }
    }

    @Override
    public String getFirstAttribute(String name) {
        System.out.println("CustomUserProvider String getFirstAttribute called....");
        if (name.equals("phone")) {
            return userEntity.getPhone();
        } else {
            return super.getFirstAttribute(name);
        }
    }

    // Attributes / roles / groups — minimal implementations

    @Override
    public Map<String, List<String>> getAttributes() {
        System.out.println("CustomUserProvider Map<String, List<String>>:getAttributes called....hello....");
//        Map<String, List<String>> attrs = super.getAttributes();
//        MultivaluedHashMap<String, String> all = new MultivaluedHashMap<>();
//        all.putAll(attrs);
//        all.add("phone", userEntity.getPhone());
//        return all;
        Map<String, List<String>> attributes = new HashMap<>();
        // Always return non-null lists, even if empty
        attributes.put(UserModel.USERNAME, List.of(getUsername()));
        attributes.put(UserModel.EMAIL, List.of(getEmail()));
        attributes.put(UserModel.FIRST_NAME, List.of(getFirstName()));
        attributes.put(UserModel.LAST_NAME, List.of(getLastName()));
        attributes.put("phone", List.of(userEntity.getPhone()));
        System.out.println("Map getAttributes attribs:"+attributes);
        return attributes;
    }


    @Override
    public Stream<String> getAttributeStream(String name) {
        System.out.println("CustomUserProvider Stream<String> getAttributeStream called....");
        if (name.equals("phone")) {
            List<String> phone = new LinkedList<>();
            phone.add(userEntity.getPhone());
            return phone.stream();
        } else {
            return super.getAttributeStream(name);
        }
    }


    @Override
    public String getFirstName() {
        return userEntity.getFirstName();
    }

    @Override
    public void setFirstName(String s) {

    }

    @Override
    public String getLastName() {
        return userEntity.getLastName();
    }

    @Override
    public void setLastName(String s) {

    }

    @Override
    public String getFederationLink() {
        return model.getId();
    }

    @Override
    public void setFederationLink(String s) {

    }
//
//    @Override
//    public String getServiceAccountClientLink() {
//        return "";
//    }
//
//    @Override
//    public void setServiceAccountClientLink(String s) {
//
//    }
//
//    @Override
//    public SubjectCredentialManager credentialManager() {
//        return null;
//    }
//
//    // Tell Keycloak there are no required actions pending
//    @Override
//    public Stream<String> getRequiredActionsStream() { return Stream.empty(); }
//    @Override public void addRequiredAction(String action) {}
//    @Override public void removeRequiredAction(String action) {}
//    @Override public void addRequiredAction(RequiredAction action) {}
//
//
//    @Override public void grantRole(RoleModel role) {}
//
//    @Override
//    public Stream<RoleModel> getRoleMappingsStream() {
//        return Stream.empty();
//    }
//
//    @Override public void deleteRoleMapping(RoleModel role) {}
//
//    @Override
//    public Stream<RoleModel> getRealmRoleMappingsStream() {
//        return Stream.empty();
//    }
//
//    @Override
//    public Stream<RoleModel> getClientRoleMappingsStream(ClientModel clientModel) {
//        return Stream.empty();
//    }
//
//    @Override public boolean hasRole(RoleModel role) { return false; }
//
//    @Override public Long getCreatedTimestamp() {
////        if (userEntity.getCreatedAt() != null) {
////            return userEntity.getCreatedAt().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
////        }
//        return System.currentTimeMillis();
//    }
//    @Override public void setCreatedTimestamp(Long timestamp) {}
    // ... implement remaining UserModel methods as no-ops or simple returns

}
