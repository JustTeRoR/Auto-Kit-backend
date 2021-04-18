package com.justterror.car_kit.security;

import com.justterror.car_kit.user.boundary.UserRepository;
import com.justterror.car_kit.user.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

public class AuthorizationIdentityStore implements IdentityStore {

    @Inject
    UserRepository repository;

    @PersistenceContext(name="users")
    private EntityManager entityManager;

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        Set<String> result;
        User expectedUser = repository.findByUsername(validationResult.getCallerPrincipal().getName());
        result = (singleton(expectedUser.getRole()));
        if (result == null) {
            result = emptySet();
        }
        return result;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return singleton(PROVIDE_GROUPS);
    }

}