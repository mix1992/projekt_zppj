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

    public <T> T find(long id, Class<T> clazz) {
        return em.find(clazz, id);
    }

    public void persist(Serializable object) {
        em.persist(object);
    }

    public void edit(Serializable object) {
        em.merge(object);
    }

    public void refresh(Serializable object) {
        em.refresh(object);
    }

    public void evict(Long id, Class clazz) {
        em.getEntityManagerFactory().getCache().evict(clazz, id);
    }

    public void clearCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public void flush() {
        em.flush();
    }

    public void remove(Serializable object) {
        em.remove(object);
    }

}
