/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.models;

/**
 *
 * @author bartek
 */

/**
 * Class implement response message for login
 */
public class LoginResponseMessage {

    private UserDTO userDTO;
    private String token;
    private String tokenExpirationTime;

    public LoginResponseMessage() {
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(String tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

}
