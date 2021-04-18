package com.justterror.auto_kit.utils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CdiConfig {

    @Produces
    @Dependent
    @PersistenceContext(unitName = "Auto-Kit")
    public EntityManager entityManager;

}