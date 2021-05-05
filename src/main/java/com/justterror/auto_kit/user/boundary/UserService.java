package com.justterror.auto_kit.user.boundary;

import com.justterror.auto_kit.security.Constants;
import com.justterror.auto_kit.user.entity.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

@Stateless
public class UserService {

    @Inject
    Logger logger;

    @PersistenceContext(name = "Auto-Kit")
    private EntityManager entityManager;

    public String login(long id, String accessToken) {
        String rawQuery = String.format("FROM User where id=%d", id);
        TypedQuery<User> query = entityManager.createQuery(rawQuery, User.class);
        if (query.getSingleResult() != null) {
            if (query.getSingleResult().getAccessToken().equals(accessToken))
            {
                return "token jwt";
            }
        }
        return "Failure";
    }

    public boolean isUserDuplicate(String username) {
        logger.info("Verifying does username = " + username + " is unique");
        String rawQuery = String.format("FROM User where username='%s'", username);
        TypedQuery<User> query = entityManager.createQuery(rawQuery, User.class);
        return query.getResultList().size() != 0;
    }

    public void registerNewUser(long id, String accessToken, String username) throws SQLException {
        User insertUser = new User(id,null, username, Constants.USER, accessToken);
        entityManager.persist(insertUser);
    }

    public void updateUsersPhone(long id, String phone) throws SQLException  {
        String rawQuery = String.format("UPDATE users set phone=%s WHERE id=%d", phone, id);
        Query query = entityManager.createNativeQuery(rawQuery);
        query.executeUpdate();
    }
}
