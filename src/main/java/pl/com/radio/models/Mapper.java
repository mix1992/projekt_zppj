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
 * Interface for operation to define flow elements mapped from one type to JSON.
 * @param <S> existing object with value in JSON form.
 * @param <T> populates existing object with values from JSON form.
 */
public interface Mapper<S, T> {

    public T populate(S entity);

    public void reversePopulate(S entity);

}
