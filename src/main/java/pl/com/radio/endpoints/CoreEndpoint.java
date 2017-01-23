/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.endpoints;

import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pl.com.radio.exceptions.ServiceException;
import pl.com.radio.models.LoginResponseMessage;
import pl.com.radio.services.AuthService;

/**
 *
 * @author bartek
 */
@Api("Core Endpoints")
@Path("core")
public class CoreEndpoint extends BaseEndpoint {

    @Context
    private HttpHeaders headers;

    @Inject
    AuthService authService;

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        try {
            String authCredentials = headers.getHeaderString("Authorization");
            LoginResponseMessage loginResponseMessage = authService.login(authCredentials);
            return Response.accepted().entity(loginResponseMessage).build();
        } catch (ServiceException ex) {
            return Response.serverError().entity(ex).build();
        }
    }
}
