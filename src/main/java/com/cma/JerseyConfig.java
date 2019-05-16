package com.cma;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.cma.service.HelloWorldService;

/**
 * @author UNGERW
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloWorldService.class);
    }


}
