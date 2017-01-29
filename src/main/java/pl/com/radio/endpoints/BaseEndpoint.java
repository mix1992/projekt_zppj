/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.endpoints;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author bartek
 */
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
/**
 * Class to create HTTP servlet suitable for a Web site
 */
public abstract class BaseEndpoint {

    @Context
    protected HttpServletRequest servletRequest;

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

}
