/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pl.com.radio.dao.AuthDAO;
import pl.com.radio.dao.UserDAO;
import pl.com.radio.entity.InternalToken;
import pl.com.radio.entity.UserEntity;
import pl.com.radio.exceptions.ServiceException;
import pl.com.radio.models.LoginResponseMessage;
import pl.com.radio.models.UserDTO;

/**
 *
 * @author bartek
 */
@Stateless
public class AuthService {

    @Inject
    private AuthDAO authDAO;

    @Inject
    private UserDAO userDAO;

    public UserEntity getUserWithToken(String internalTokenValue) {
        return authDAO.findUserByValidInternalToken(internalTokenValue);
    }

    public LoginResponseMessage login(String authCredentials) throws ServiceException {
        if (authCredentials == null) {
            throw new ServiceException(0, "credencialNotFound");
        }

        String emailAndPassword = decode(authCredentials);

        final StringTokenizer tokenizer = new StringTokenizer(
                emailAndPassword, ":");
        final String email = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        final String shaPassword = Utils.sha256(password.getBytes());
        UserEntity u = userDAO.findUserByEmail(email);

        checkUserProperties(u, shaPassword);

        InternalToken token = generateToken(u);

        LoginResponseMessage loginMessage = createLoginResponseMessage(u, token);

        authDAO.evict(u.getId(), UserEntity.class);

        return loginMessage;
    }

    private void checkUserProperties(UserEntity u, final String shaPassword) throws ServiceException {
        if (u == null) {
            throw new ServiceException(0, "userNotRegistred");
        }
        if (!shaPassword.equals(u.getPassword())) {
            throw new ServiceException(0, "wrongPassword");
        }
    }

    private InternalToken generateToken(UserEntity u) {
        InternalToken token;
        if (u.getInternalToken() != null) {
            token = u.getInternalToken();
        } else {
            token = new InternalToken();
            token.setUser(u);
            u.setInternalToken(token);
        }
        token.setValue(UUID.randomUUID().toString());
        token.setExpirationTime(LocalDateTime.now().plusHours(1L));
        authDAO.persist(token);
        authDAO.evict(token.getId(), InternalToken.class);
        return token;
    }

    private LoginResponseMessage createLoginResponseMessage(UserEntity u, InternalToken token) {
        LoginResponseMessage loginMessage = new LoginResponseMessage();
        loginMessage.setUserDTO(new UserDTO().populate(u));
        loginMessage.setToken(token.getValue());
        loginMessage.setTokenExpirationTime(token.getExpirationTime().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return loginMessage;
    }

    public void extendToken(String tokenValue) throws ServiceException {
        InternalToken token = authDAO.getInternalTokenByValue(tokenValue);
        if (token == null) {
            throw new ServiceException(0, "wrongToken");
        }
        token.setExpirationTime(LocalDateTime.now().plusHours(1L));

        authDAO.edit(token);

        authDAO.evict(token.getId(), InternalToken.class);
    }

    private String decode(String authCredentials) throws ServiceException {
        try {
            final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                    + " ", "");
            return decodeAuthenticationHeader(encodedUserPassword);
        } catch (UnsupportedEncodingException ex) {
            throw new ServiceException(0, "decodeCredencialFeild");
        }
    }

    private String decodeAuthenticationHeader(final String encodedUserPassword) throws UnsupportedEncodingException {
        String emailAndPassword;
        byte[] decodedBytes = Base64.getDecoder().decode(
                encodedUserPassword);
        emailAndPassword = new String(decodedBytes, "UTF-8");
        emailAndPassword = URLDecoder.decode(emailAndPassword, "UTF-8");
        return emailAndPassword;
    }

}
