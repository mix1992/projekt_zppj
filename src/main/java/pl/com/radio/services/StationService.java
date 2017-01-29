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
import pl.com.radio.exceptions.ServiceException;
import pl.com.radio.models.StationDTO;

/**
 *
 * @author bartek
 */
@Stateless
/**
 * Class implement service method to handle with station configuration.
 */

public class StationService {

    @Inject
    private StationDAO stationDAO;

    /**
     * Function to create new station
     * @param stationDTO is data transfer object to mapping station on JSON form
     * @return new populate entity of station
     * @throws ServiceException
     */
    public StationDTO addStation(StationDTO stationDTO) throws ServiceException {
        checkStationDTO(stationDTO);
        StationEntity stationEntity = new StationEntity();
        stationDTO.reversePopulate(stationEntity);
        stationDAO.create(stationEntity);
        return new StationDTO().populate(stationEntity);
    }

    /**
     * Cast Object to List of station data transfer object
     * @return station parameters
     */
    public List<StationDTO> getStations() {
        List<StationEntity> stations = stationDAO.findAllStations();
        List<StationDTO> stationsDTO = new ArrayList<>();
        stations.forEach(station -> stationsDTO.add(new StationDTO().populate(station)));
        stations.forEach(station -> stationDAO.evict(station.getId(), StationEntity.class));
        return stationsDTO;
    }

    /**
     * Function responsible to delete user
     * @param stationId is Id number of station wich want to delete
     * @return station entity without deleted station
     */
    public StationDTO deleteStation(Long stationId) {
        StationEntity stationEntity = stationDAO.find(stationId);
        if (stationEntity == null) {
            //TODO: nie znaleziono
        }
        stationDAO.remove(stationEntity);
        return new StationDTO().populate(stationEntity);
    }

    /**
     *
     * @param stationDTO
     * @throws ServiceException
     */
    private void checkStationDTO(StationDTO stationDTO) throws ServiceException {
        if (stationDTO.getName() == null || stationDTO.getName().isEmpty()) {
            throw new ServiceException(0, "stationNameNotFound");
        }
        if (stationDTO.getPath() == null || stationDTO.getPath().isEmpty()) {
            throw new ServiceException(0, "stationPathNotFound");
        }
    }

}
