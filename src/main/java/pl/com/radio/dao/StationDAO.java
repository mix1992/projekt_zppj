/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.dao;

import java.util.List;
import javax.ejb.Stateless;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
