/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pl.com.radio.dao.StationDAO;
import pl.com.radio.entity.StationEntity;
import pl.com.radio.models.StationDTO;

/**
 *
 * @author bartek
 */
@Stateless
public class StationService {

    @Inject
    private StationDAO stationDAO;

    public StationDTO addStation(StationDTO stationDTO) {
        checkStationDTO(stationDTO);
        StationEntity stationEntity = new StationEntity();
        stationDTO.reversePopulate(stationEntity);
        stationDAO.create(stationEntity);
        return new StationDTO().populate(stationEntity);
    }

    public List<StationDTO> getStations() {
        List<StationEntity> stations = stationDAO.findAllStations();
        List<StationDTO> stationsDTO = new ArrayList<>();
        stations.forEach(station -> stationsDTO.add(new StationDTO().populate(station)));
        stations.forEach(station -> stationDAO.evict(station.getId(), StationEntity.class));
        return stationsDTO;
    }

    public StationDTO deleteStation(Long stationId) {
        StationEntity stationEntity = stationDAO.find(stationId);
        if (stationEntity == null) {
            //TODO: nie znaleziono
        }
        stationDAO.remove(stationEntity);
        return new StationDTO().populate(stationEntity);
    }

    private void checkStationDTO(StationDTO stationDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
