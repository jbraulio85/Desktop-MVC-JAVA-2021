package org.kalum.core.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="alumno")
@NamedQueries({@NamedQuery(name="Alumno.findAll", query ="from Alumno")})
public class Alumno implements Serializable {

    private final StringProperty carne;
    private final StringProperty noExpediente;
    private final StringProperty apellidos;
    private final StringProperty nombres;
    private final StringProperty email;
    private List<AsignacionAlumno> asignacionAlumnos;

    public Alumno() {
        this.carne = new SimpleStringProperty();
        this.noExpediente = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.nombres = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    @Override
    public String toString(){
        return this.getCarne();
    }

    @Id
    @Column(name = "carne")
    public String getCarne(){
        return this.carne.get();
    }

    public void setCarne(String carne){
        this.carne.set(carne);
    }

    public StringProperty carne (){
        return this.carne;
    }
    @Column(name = "noExpediente")
    public String getNoExpediente(){
        return this.carne.get();
    }

    public void setNoExpediente(String noExpediente){
        this.noExpediente.set(noExpediente);
    }

    public StringProperty noExpediente(){
        return this.noExpediente;
    }

    @Column(name="apellidos")
    public String getApellidos(){
        return this.apellidos.get();
    }

    public void setApellidos(String apellidos){
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidos(){
        return this.apellidos;
    }

    @Column(name="nombres")
    public String getNombres(){
        return this.nombres.get();
    }

    public void setNombres(String nombres){
        this.nombres.set(nombres);
    }

    public StringProperty nombres(){
        return this.nombres;
    }

    @Column(name="email")
    public String getEmail(){
        return this.email.get();
    }

    public void setEmail(String email){
        this.email.set(email);
    }

    public StringProperty email(){
        return this.email;
    }

    @OneToMany (mappedBy = "alumno", fetch = FetchType.LAZY)
    public List<AsignacionAlumno> getAsignacionAlumnos() {
        return asignacionAlumnos;
    }

    public void setAsignacionAlumnos(List<AsignacionAlumno> asignacionAlumnos) {
        this.asignacionAlumnos = asignacionAlumnos;
    }
}
