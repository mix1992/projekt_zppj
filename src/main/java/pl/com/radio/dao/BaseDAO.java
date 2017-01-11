/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bartek
 */
public class BaseDAO {

    @PersistenceContext
    protected EntityManager em;

    public void create(Serializable userEntity) {
        em.persist(userEntity);
    }

}
