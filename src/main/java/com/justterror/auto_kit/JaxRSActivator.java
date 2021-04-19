package com.justterror.auto_kit;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static com.justterror.auto_kit.security.Constants.ADMIN;
import static com.justterror.auto_kit.security.Constants.USER;

@DeclareRoles({ADMIN, USER})
@ApplicationPath("/api")
public class JaxRSActivator extends Application {
}
