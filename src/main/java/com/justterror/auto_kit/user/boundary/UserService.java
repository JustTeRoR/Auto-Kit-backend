package com.justterror.auto_kit.user.boundary;

import com.justterror.auto_kit.security.Constants;
import com.justterror.auto_kit.user.boundary.UserRepository;
import com.justterror.auto_kit.user.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Logger;

@Stateless
public class UserService {

    @Inject
    Logger logger;

    @Inject
    UserRepository repository;

    @PersistenceContext(name="users")
    private EntityManager entityManager;
    private static final SecureRandom RAND = new SecureRandom();



    public String login(String username, String password) {
        User user = repository.findByUsername(username);
        if (user != null) {
            String hashedInputPwd = getMD5Hash(password);
            if (user.getPassword().equals(hashedInputPwd))
            {
                return "token jwt";
            }
        }
        return "Failure";
    }

    private boolean isUserDuplicate(String username) {
        logger.info("Verifing does username = " + username + "is unique");
        User user = repository.findByUsername(username);
        return user != null;
    }

    public static String getMD5Hash(String input) {
        StringBuffer sb = new StringBuffer();

        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] mdbytes = md.digest();

            //convert the byte to hex format
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff)
                        + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return sb.toString();
    }
}
