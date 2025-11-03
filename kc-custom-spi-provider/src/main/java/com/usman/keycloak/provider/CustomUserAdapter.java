package com.usman.keycloak.provider;

import com.usman.keycloak.model.MyUserEntity;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.*;
import org.keycloak.storage.ReadOnlyException;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

import java.util.*;
import java.util.stream.Stream;

public class CustomUserAdapter extends AbstractUserAdapterFederatedStorage {

    private final RealmModel realm;
    private final ComponentModel model;
    private final MyUserEntity userEntity; // your POJO
    private final String keycloakId;

    public CustomUserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, MyUserEntity userEntity) {
        super(session, realm, model);
        this.realm=realm;
        this.model = model;
        this.userEntity = userEntity;
        this.keycloakId= StorageId.keycloakId(model, String.valueOf(userEntity.getId()));
        System.out.println("CustomUserAdapter - created keycloakId ="+this.keycloakId);
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
        return this.keycloakId;
    }

    @Override
    public String getUsername() {
        System.out.println("CustomUserProvider getUsername is called:"+userEntity.getUsername());
        return userEntity.getUsername() != null ? userEntity.getUsername() : "";
    }
    @Override
    public void setUsername(String username) {
        throw new ReadOnlyException("Username cannot be changed for federated users:"+username);
    }

    @Override
    public String getEmail() {
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

    // Attributes / roles / groups — minimal implementations

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


    @Override
    public Map<String, List<String>> getAttributes() {
        System.out.println("CustomUserProvider Map<String, List<String>>:getAttributes called....");
        Map<String, List<String>> attributes = new HashMap<>();
        // Always return non-null lists, even if empty
        attributes.put(UserModel.USERNAME, List.of(getUsername()));
        attributes.put(UserModel.EMAIL, List.of(getEmail()));
        attributes.put(UserModel.FIRST_NAME, List.of(getFirstName()));
        attributes.put(UserModel.LAST_NAME, List.of(getLastName()));
        attributes.put("phone", List.of(userEntity.getPhone()));
        System.out.println("CustomUserProvider Map getAttributes :"+attributes);
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
    public String getFederationLink() {
        return model.getId();
    }

    @Override
    public void setFederationLink(String s) {

    }

    //Add realm and client roles for each user.

    @Override
    public Stream<RoleModel> getRealmRoleMappingsStream() {
        System.out.println("CustomUserProvider getRealmRoleMappingsStream called...");
        Set<RoleModel> roles = new HashSet<>();
        // Add default realm roles (these must exist in KC)
        Stream<RoleModel> rolesStream = realm.getRolesStream();
        rolesStream.forEach(role ->{
            RoleModel roleById = realm.getRoleById(role.getId());
            System.out.println("CustomUserProvider realm role ids :"+role.getId() +", Name :"+ roleById.getName());
            roles.add(roleById);
        });

//        RoleModel defaultRole = realm.getRole("RL_MANAGER");
//        System.out.println("CustomUserProvider defaultRole:"+defaultRole);
//        if (defaultRole != null) roles.add(defaultRole);
//
//        RoleModel adminRole = realm.getRole("RL_ADMIN");
//        System.out.println("CustomUserProvider adminRole:"+adminRole);
//        if (adminRole != null) {
//            roles.add(adminRole);
//        }

        return roles.stream();
    }

    @Override
    public Stream<RoleModel> getClientRoleMappingsStream(ClientModel client) {
        System.out.println("CustomUserProvider getClientRoleMappingsStream called...");
        System.out.println("CustomUserProvider client:"+client.getClientId());

        if ("usmanclient".equals(client.getClientId())) {
            Set<RoleModel> clientRoles = new HashSet<>();
            Stream<RoleModel> rolesStream = client.getRolesStream();

            rolesStream.forEach(role ->{
                RoleModel roleById = realm.getRoleById(role.getId());
                System.out.println("CustomUserProvider client role ids :"+role.getId() +", Name :"+roleById.getName());
                clientRoles.add(roleById);
            });

//            RoleModel viewRole = client.getRole("VIEW");
//            System.out.println("CustomUserProvider viewRole:"+viewRole);
//            if (viewRole != null) clientRoles.add(viewRole);
//
//            RoleModel userRole = client.getRole("USER");
//            System.out.println("CustomUserProvider userRole:"+userRole);
//            if (userRole != null) clientRoles.add(userRole);

            return clientRoles.stream();
        }
        return Stream.empty();
    }

    @Override
    public Stream<RoleModel> getRoleMappingsStream(){
        System.out.println("CustomUserProvider getRoleMappingsStream called...");

        String myClientId = "usmanclient";

        Optional<ClientModel> myClientOpt = realm.getClientsStream()
                .filter(client -> myClientId.equals(client.getClientId())).findFirst();

        if (myClientOpt.isPresent()) {
            ClientModel myClient = myClientOpt.get();
            System.out.println("Found client: "+ myClient.getClientId());
            return Stream.concat(
                    getRealmRoleMappingsStream(),
                    getClientRoleMappingsStream(myClient)
            );
        } else {
            System.out.println("Client '{}' not found in realm {}"+ realm.getName());
            return Stream.empty();
        }
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
