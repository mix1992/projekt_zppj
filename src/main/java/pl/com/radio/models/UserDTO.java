/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.models;

import pl.com.radio.entity.UserEntity;

/**
 *
 * @author bartek
 */
public class UserDTO implements Mapper<UserEntity, UserDTO> {

    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Override
    public UserDTO populate(UserEntity entity) {
        this.id = entity.getId();
        this.login = entity.getLogin();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        return this;
    }

    @Override
    public void reversePopulate(UserEntity entity) {
        entity.setEmail(email);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setLogin(login);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
