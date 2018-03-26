package org.softuni.residentevil.filters;

import org.softuni.residentevil.annotations.PreAuthenticate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            if (method.getMethod().isAnnotationPresent(PreAuthenticate.class)) {
                PreAuthenticate annotation = method.getMethod().getDeclaredAnnotation(PreAuthenticate.class);
                Object userId = request.getSession().getAttribute("userId");

                if (annotation.loggedIn()) {
                    return userId != null
                            && request.getSession().getAttribute("role").equals(annotation.inRole());
                } else {
                    return userId == null;
                }
            }
        }

        return true;
    }
}