/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.dao;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pl.com.radio.entity.InternalToken;
import pl.com.radio.entity.UserEntity;

/**
 *
 * @author bartek
 */
@Stateless
public class AuthDAO extends BaseDAO {

    public UserEntity findUserByValidInternalToken(String internalTokenValue) {
        Query query = em.createNamedQuery("InternalToken.userByValidToken");
        query.setParameter("value", internalTokenValue);
        query.setParameter("now", LocalDateTime.now());
        List result = query.getResultList();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return (UserEntity) result.get(0);
    }

    public InternalToken getInternalTokenByValue(String tokenValue) {
        Query q = em.createNamedQuery("InternalToken.byValue");
        q.setParameter("value", tokenValue);
        List result = q.getResultList();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return (InternalToken) result.get(0);
    }

}
