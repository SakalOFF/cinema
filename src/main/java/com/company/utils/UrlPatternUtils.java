package com.company.utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;

public class UrlPatternUtils {

    private static boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {

        Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();

        for (String servletName : map.keySet()) {
            ServletRegistration sr = map.get(servletName);

            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }

        }
        return false;
    }


    public static String getUrlPattern(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();

        String urlPattern;
        if (pathInfo != null) {
            pathInfo = pathInfo.split("/")[1];
            urlPattern = pathInfo + "/*";
            return urlPattern;
        }
        return  "/*";
    }
}
