/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.dao;

import java.util.List;
import javax.ejb.Stateless;
import pl.com.radio.entity.UserEntity;

/**
 *
 * @author bartek
 */
@Stateless
public class UserDAO extends BaseDAO {

    public UserEntity find(long id) {
        return super.find(id, UserEntity.class);
    }

    public List<UserEntity> findAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
