/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bartek
 */
@Entity
@Table(schema = "web", indexes = {
    @Index(columnList = "value, expirationtime")
})
@NamedQueries({
    @NamedQuery(name = "InternalToken.userByValidToken", query = "SELECT t.user FROM InternalToken t WHERE t.value = :value and t.expirationTime > :now"),
    @NamedQuery(name = "InternalToken.byValue", query = "SELECT t FROM InternalToken t WHERE t.value = :value")
})
public class InternalToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime expirationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    @Column(unique = true)
    private String value;

    public Long getId() {
        return id;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
