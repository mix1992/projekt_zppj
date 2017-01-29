/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import pl.com.radio.models.StationDTO;

/**
 *
 * @author bartek
 */

/**
 * Class implement service method like start radio, stop radio, current played station
 * to use radio how user need.
 */
@ApplicationScoped
public class RadioService {

    private Runtime runtime = Runtime.getRuntime();
    private Process radio;
    private String currentStation = "";

    public void startRadio(String stationPath) throws IOException {
        stopRadio();
        radio = runtime.exec("cvlc  " + stationPath + " &");
        currentStation = stationPath;
    }

    public void stopRadio() throws IOException {
        if (radio != null) {
            radio.destroyForcibly();
        }
        currentStation = "";
    }

    public Runtime getRuntime() {
        return runtime;
    }

    public void setRuntime(Runtime runtime) {
        this.runtime = runtime;
    }

    public Process getRadio() {
        return radio;
    }

    public void setRadio(Process radio) {
        this.radio = radio;
    }

    public String getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(String currentStation) {
        this.currentStation = currentStation;
    }

    public StationDTO getCurrentPplayedStation() {
        StationDTO stationDTO = new StationDTO();
        stationDTO.setName("currentPlayedStation");
        stationDTO.setPath(currentStation);
        return stationDTO;
    }

}
