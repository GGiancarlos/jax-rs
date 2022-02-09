package org.example;

import org.example.jpa.repository.EntityManagerInitializer;
import org.example.service.AlumnoServiceImpl;
import org.example.service.IAlumnoService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

public class GrizzlyHttpServer
{
    private static final Logger logger = Logger.getLogger(GrizzlyHttpServer.class.getName());
    public static final String BASE_URI = "http://localhost:5002/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("org.example.controller");
        rc.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(EntityManagerInitializer.class);
            }
        });

        rc.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(AlumnoServiceImpl.class).to(IAlumnoService.class);
            }
        });

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main( String[] args ) throws IOException
    {
        final HttpServer server = startServer();
        logger.info(String.format("Jersey app started with endpoints available at " + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
