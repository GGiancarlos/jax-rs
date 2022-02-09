package org.example.service;

import jakarta.inject.Inject;
import jakarta.persistence.TypedQuery;
import org.example.entity.AlumnoBean;
import org.example.jpa.model.TblAlumno;
import org.example.jpa.repository.EntityManagerInitializer;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

    @Inject
    private EntityManagerInitializer em;

    @Override
    public List<TblAlumno> obtenerAlumnos() {
        //em
        TypedQuery<TblAlumno> query = em.getEntityManager().createQuery("select t from TblAlumno t", TblAlumno.class);
        return query.getResultList();
    }

    @Override
    public TblAlumno obtenerAlumno(String dni) {
        return em.entityManager().find(TblAlumno.class, dni);
    }

    @Override
    public void actualizar(AlumnoBean alumno) {
        em.getEntityManager().getTransaction().begin();
        TblAlumno tblAlumno = new TblAlumno();
        tblAlumno.setDni(alumno.getDni());
        tblAlumno.setNombre(alumno.getNombre());
        tblAlumno.setApellido(alumno.getApellido());
        tblAlumno.setCelular(alumno.getCelular());

        em.getEntityManager().merge(tblAlumno);
        em.getEntityManager().getTransaction().commit();
    }

    @Override
    public void eliminar(String dni) {
        em.getEntityManager().getTransaction().begin();
        TblAlumno alumno = em.entityManager().find(TblAlumno.class, dni);
        em.getEntityManager().remove(alumno);
        em.getEntityManager().getTransaction().commit();
    }

    @Override
    public void registrar(AlumnoBean alumno) {
        em.getEntityManager().getTransaction().begin();
        TblAlumno tblAlumno = new TblAlumno();
        tblAlumno.setDni(alumno.getDni());
        tblAlumno.setNombre(alumno.getNombre());
        tblAlumno.setApellido(alumno.getApellido());
        tblAlumno.setCelular(alumno.getCelular());
        em.getEntityManager().persist(tblAlumno);
        em.getEntityManager().getTransaction().commit();
    }
}
