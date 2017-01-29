/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pl.com.radio.entity.UserEntity;

/**
 *
 * @author bartek
 */
@Stateless
/**
 * Class using to handle support with database to find users by id or all
 */
public class UserDAO extends BaseDAO {

    public UserEntity find(long id) {
        return super.find(id, UserEntity.class);
    }

    public List<UserEntity> findAllUsers() {
        Query q = em.createNamedQuery("User.all");
        List<UserEntity> rs = q.getResultList();
        return rs;
    }

    public UserEntity findUserByEmail(String email) {
        Query q = em.createNamedQuery("User.byEmail");
        q.setParameter("email", email);
        q.setMaxResults(1);
        List<UserEntity> rs = q.getResultList();
        return rs.isEmpty() ? null : rs.get(0);
    }

}
