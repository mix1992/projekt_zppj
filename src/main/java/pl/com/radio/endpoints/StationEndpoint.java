/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.endpoints;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import pl.com.radio.models.StationDTO;
import pl.com.radio.services.StationService;

/**
 *
 * @author bartek
 */
@Path("station")
public class StationEndpoint extends BaseEndpoint {

    @Inject
    private StationService stationService;

    @POST
    public Response addStation(StationDTO stationDTO) {
        StationDTO responseDTO = stationService.addStation(stationDTO);
        return Response.accepted().entity(responseDTO).build();
    }

    @GET
    public Response getStations() {
        List<StationDTO> responseDTOs = stationService.getStations();
        return Response.ok().entity(responseDTOs).build();
    }

    @DELETE
    @Path("{stationId}")
    public Response deleteStation(@PathParam("userId") Long stationId) {
        StationDTO stationDTO = stationService.deleteStation(stationId);
        return Response.accepted().entity(stationDTO).build();
    }

}
