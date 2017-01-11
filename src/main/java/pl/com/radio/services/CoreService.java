/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.services;

import javax.ejb.Stateless;
import pl.com.radio.models.LoginRequestMessage;
import pl.com.radio.models.LoginResponseMessage;

/**
 *
 * @author bartek
 */
@Stateless
public class CoreService {

    public LoginResponseMessage login(LoginRequestMessage loginMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
