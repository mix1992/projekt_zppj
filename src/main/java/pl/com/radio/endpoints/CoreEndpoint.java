/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.endpoints;

import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import pl.com.radio.models.LoginRequestMessage;
import pl.com.radio.models.LoginResponseMessage;
import pl.com.radio.services.CoreService;

/**
 *
 * @author bartek
 */
@Api("Core Endpoints")
@Path("core")
public class CoreEndpoint extends BaseEndpoint {

    @Inject
    CoreService coreService;

    @POST
    public Response login(LoginRequestMessage loginMessage) {
        LoginResponseMessage loginResponseMessage = coreService.login(loginMessage);
        return Response.accepted().entity(loginResponseMessage).build();
    }
}
