/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.radio;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.reflections.Reflections;

/**
 *
 * @author bartek
 */
@ApplicationPath("rest")
public class JaxRsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        s.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        addRestResourceClassess(s);
        return s;
    }

    private void addRestResourceClassess(Set<Class<?>> resources) {
        Reflections reflections = new Reflections("pl.com.radio.endpoints");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(javax.ws.rs.Path.class);
        for (Class c : annotated) {
            resources.add(c);
        }
    }
}
