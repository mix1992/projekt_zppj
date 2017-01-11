/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author bartek
 */
@ApplicationScoped
public class RadioService {

    private Runtime runtime = Runtime.getRuntime();
    private Process radio;

    public void startRadio(String stationPath) throws IOException {
        stopRadio();
        radio = runtime.exec("cvlc  " + stationPath + " &");
    }

    public void stopRadio() throws IOException {
        if (radio != null) {
            radio.destroyForcibly();
        }
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

}
