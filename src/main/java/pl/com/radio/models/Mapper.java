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
public interface Mapper<S, T> {

    public T populate(S entity);

    public void reversePopulate(S entity);

}
