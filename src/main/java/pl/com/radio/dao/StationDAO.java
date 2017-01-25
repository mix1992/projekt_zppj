/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import pl.com.radio.entity.StationEntity;

/**
 *
 * @author bartek
 */
@Stateless
public class StationDAO extends BaseDAO {

    public StationEntity find(long id) {
        return super.find(id, StationEntity.class);
    }

    public List<StationEntity> findAllStations() {
        Query q = em.createNamedQuery("Station.all");
        return q.getResultList();
    }

}
