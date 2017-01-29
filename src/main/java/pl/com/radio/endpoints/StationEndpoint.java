/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.endpoints;

import annotations.AuthRequired;
import io.swagger.annotations.Api;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import pl.com.radio.exceptions.ServiceException;
import pl.com.radio.models.StationDTO;
import pl.com.radio.services.StationService;

/**
 *
 * @author bartek
 *
 * Class extends the BaseEndpoint type and represents endpoint that can send and
 * receive message to add new station or delete station
 */
@Api("Station Endpoints")
@Path("station")
@AuthRequired
public class StationEndpoint extends BaseEndpoint {

    @Inject
    private StationService stationService;

    @POST
    public Response addStation(StationDTO stationDTO) {
        try {
            StationDTO responseDTO = stationService.addStation(stationDTO);
            return Response.accepted().entity(responseDTO).build();
        } catch (ServiceException ex) {
            return Response.serverError().entity(ex).build();
        }
    }

    @GET
    public Response getStations() {
        List<StationDTO> responseDTOs = stationService.getStations();
        return Response.ok().entity(responseDTOs).build();
    }

    @DELETE
    @Path("{stationId}")
    public Response deleteStation(@PathParam("stationId") Long stationId) {
        StationDTO stationDTO = stationService.deleteStation(stationId);
        return Response.accepted().entity(stationDTO).build();
    }

}
