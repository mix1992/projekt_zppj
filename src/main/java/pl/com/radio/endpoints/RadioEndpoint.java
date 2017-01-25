/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.endpoints;

import annotations.AuthRequired;
import io.swagger.annotations.Api;
import java.io.IOException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import pl.com.radio.models.StationPathDTO;
import pl.com.radio.services.RadioService;

/**
 *
 * @author bartek
 */
@Api("Radio Endpoints")
@Path("radio")
@AuthRequired
public class RadioEndpoint extends BaseEndpoint {

    @Inject
    private RadioService radioService;

    @POST
    public Response startRadio(StationPathDTO stationPath) throws IOException {
        radioService.startRadio(stationPath.getPath());
        return Response.ok().build();
    }

    @GET
    public Response stopRadio() throws IOException {
        radioService.stopRadio();
        return Response.ok().build();
    }

}
