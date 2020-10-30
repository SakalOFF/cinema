package com.company.utils;

import com.company.model.entity.Role;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private final String user;
    private final Role role;
    private final HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, Role role, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.role = role;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        return role.equals(this.role.name());
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }
        return () -> user;
    }
}
