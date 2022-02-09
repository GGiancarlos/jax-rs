package org.example.service;

import org.example.entity.AlumnoBean;
import org.example.jpa.model.TblAlumno;

import java.util.List;

public interface IAlumnoService {
    List<TblAlumno> obtenerAlumnos();

    TblAlumno obtenerAlumno(String dni);

    void actualizar(AlumnoBean alumno);

    void eliminar(String dni);

    void registrar(AlumnoBean alumno);

}
