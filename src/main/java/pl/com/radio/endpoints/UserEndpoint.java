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
import pl.com.radio.models.UserDTO;
import pl.com.radio.services.UserService;

/**
 *
 * @author bartek
 *
 * Class extends the BaseEndpoint type and represents endpoint that can send and
 * receive message to user service like get user, delete user or add user.
 */
@Api("User Endpoints")
@Path("user")
@AuthRequired
public class UserEndpoint extends BaseEndpoint {

    @Inject
    private UserService userService;

    @POST
    public Response addUser(UserDTO userDTO) {
        try {
            UserDTO responseDTO = userService.createUser(userDTO);
            return Response.accepted().entity(responseDTO).build();
        } catch (ServiceException ex) {
            return Response.serverError().entity(ex).build();
        }
    }

    @GET
    public Response getUsers() {
        List<UserDTO> responseDTOs = userService.getUsers();
        return Response.ok().entity(responseDTOs).build();
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") Long userId) {
        try {
            UserDTO userDTO = userService.deleteUser(userId);
            return Response.accepted().entity(userDTO).build();
        } catch (ServiceException ex) {
            return Response.serverError().entity(ex).build();
        }
    }
}
