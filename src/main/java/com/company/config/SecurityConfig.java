package com.company.config;

import com.company.model.entity.Role;

import java.util.*;

public class SecurityConfig {

    private static final Map<Role, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        List<String> urlPatterns = new ArrayList<>();

        urlPatterns.add("logout/*");
        urlPatterns.add("tickets/*");

        mapConfig.put(Role.USER, urlPatterns);

        urlPatterns = new ArrayList<>();

        urlPatterns.add("logout/*");
        urlPatterns.add("tickets/*");
        urlPatterns.add("redactor/*");
        urlPatterns.add("sessions/*");

        mapConfig.put(Role.ADMIN, urlPatterns);
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }

}
