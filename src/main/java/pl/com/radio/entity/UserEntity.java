/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author bartek
 */
@Entity
@Table(schema = "web", name = "user",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"login"})
        }
)
@NamedQueries({
    @NamedQuery(name = "User.byEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
    @NamedQuery(name = "User.all", query = "SELECT u FROM UserEntity u")
})
/**
 * Class to serialization objects in user entity in table
 * @param id is id number of user
 * @param login is user login
 * @param firstName is user first name
 * @param lastName is user surname
 * @param email is user email
 * @param password is user password
 */
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    private InternalToken internalToken;

    @Column(name = "password")
    private String password;


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

    public InternalToken getInternalToken() {
        return internalToken;
    }

    public void setInternalToken(InternalToken internalToken) {
        this.internalToken = internalToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
