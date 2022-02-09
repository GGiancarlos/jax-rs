package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.AlumnoBean;
import org.example.jpa.model.TblAlumno;
import org.example.service.IAlumnoService;

import java.util.List;

@Path("/alumno")
public class AlumnoRest {
    @Inject
    private IAlumnoService alumnoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TblAlumno> listarAlumnos() {
        return alumnoService.obtenerAlumnos();
    }

    @GET
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public TblAlumno listarAlumno(@PathParam("dni") String dni) {
        return alumnoService.obtenerAlumno(dni);
    }

    @PUT
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarAlumno(@PathParam("dni") String dni, AlumnoBean alumno) {
        alumno.setDni(dni);
        alumnoService.actualizar(alumno);
    }

    @DELETE
    @Path("/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public void eliminarAlumno(@PathParam("dni") String dni) {
        alumnoService.eliminar(dni);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarAlumno(AlumnoBean alumno) {
        alumnoService.registrar(alumno);
    }
}
