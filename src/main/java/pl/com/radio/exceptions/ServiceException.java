/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.exceptions;

/**
 *
 * @author bartek
 */
public class ServiceException extends Exception {

    private final int code;

    public ServiceException(int code) {
        this.code = code;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(int code, String string, Throwable thrwbl) {
        super(string, thrwbl);
        this.code = code;
    }

    public ServiceException(int code, Throwable thrwbl) {
        super(thrwbl);
        this.code = code;
    }

    public ServiceException(int code, String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
