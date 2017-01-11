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
import pl.com.radio.models.UserDTO;
import pl.com.radio.services.UserService;

/**
 *
 * @author bartek
 */
@Path("user")
public class UserEndpoint extends BaseEndpoint {

    @Inject
    private UserService userService;

    @POST
    public Response addUser(UserDTO userDTO) {
        UserDTO responseDTO = userService.createUser(userDTO);
        return Response.accepted().entity(responseDTO).build();
    }

    @GET
    public Response getUsers() {
        List<UserDTO> responseDTOs = userService.getUsers();
        return Response.ok().entity(responseDTOs).build();
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") Long userId) {
        UserDTO userDTO = userService.deleteUser(userId);
        return Response.accepted().entity(userDTO).build();
    }
}
