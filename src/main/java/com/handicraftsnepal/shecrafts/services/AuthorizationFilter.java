package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.User;
import lombok.SneakyThrows;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
@Transactional
public class AuthorizationFilter implements ContainerRequestFilter {
    @PersistenceContext
    private EntityManager entityManager;
    @Context
    private ResourceInfo resourceInfo;
    private Object NotAuthorizedException;

    @SneakyThrows
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        //get the resource class which matches with the requested URL
        //extract the roles declared by it
        Class<?> resourceClass=resourceInfo.getResourceClass();
        List<Role> classRoles=extractRoles(resourceClass);

//        get the resource method which matches with the requested URL
//        extract the roles declared by it
        Method resourceMethod=resourceInfo.getResourceMethod();
        List<Role> methodRoles=extractRoles(resourceMethod);

        try{
            //check if the user is allowed to execute the method
            //the method annotations override the class annotations
            if(!methodRoles.isEmpty()){
                permitRole(methodRoles,requestContext);
//                checkPermissions(methodRoles);
            } else{
                checkPermissions(methodRoles);
//                permitRole(methodRoles,requestContext);
//                return;
            }
        } catch (Exception e){
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }

    }

    //extract the roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement){
        if(annotatedElement == null){
            return new ArrayList<Role>();
        } else {
            Secured secured=annotatedElement.getAnnotation(Secured.class);
            if(secured == null){
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles=secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(List<Role> allowedRoles) throws Throwable {
        //check if the user contains one of the allowed roles
        //throw an exception if the user has not permission to execute the method
        if(allowedRoles == null){
            return;
        }else{
            throw (Throwable) NotAuthorizedException;

        }
    }

    private void permitRole(List<Role> allowedRoles,ContainerRequestContext requestContext) throws Throwable {
        //extract role of loged in user
        //if both roll doesn't matches, throw unauthorized exception
        String userName=requestContext.getHeaderString("username");
        User user = entityManager.createQuery("select e from User e where userName =:userName", User.class).setParameter("userName",userName).getSingleResult();
        String role=user.getRole();
        for(Role r:allowedRoles){
            System.out.println(r.toString());
            System.out.println(role);
            if(role.equals(r)){
                return;
            }

        }
//        if(role.equals(allowedRoles.get(0))){
//            return;
//        }else {
//            throw (Throwable) NotAuthorizedException;
//        }
//        throw (Throwable) NotAuthorizedException;
    }

}
