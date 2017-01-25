/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio.filters;

import annotations.AuthRequired;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import pl.com.radio.entity.UserEntity;
import pl.com.radio.exceptions.ServiceException;
import pl.com.radio.services.AuthService;

/**
 *
 * @author bartek
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
@AuthRequired
public class AuthRequestFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest servletRequest;

    @Inject
    private AuthService authService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String internalToken = requestContext.getHeaderString("Session-Token");
        if (internalToken == null || internalToken.trim().isEmpty()) {
            unauthorized(requestContext);
            return;
        }

        UserEntity user = authService.getUserWithToken(internalToken);

        if (user == null) {
            unauthorized(requestContext);
            return;
        }

        try {
            authService.extendToken(internalToken);
        } catch (ServiceException ex) {
            unauthorized(requestContext);
            return;
        }

        servletRequest.setAttribute("user", user);
    }

    private void unauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response
                .status(Response.Status.UNAUTHORIZED)
                .build());
    }

}
