package org.kalum.core.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="instructor")
@NamedQueries({@NamedQuery(name="Instructor.findAll",query="from Instructor")})
public class Instructor implements Serializable {
    private final StringProperty instructorId;
    private final StringProperty apellidos;
    private final StringProperty comentario;
    private final StringProperty direccion;
    private final StringProperty status;
    private final StringProperty foto;
    private final StringProperty nombres;
    private final StringProperty telefono;
    private List<Clase> clases;

    public Instructor(){
        this.instructorId = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.comentario = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.foto = new SimpleStringProperty();
        this.nombres = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
    }

    @Override
    public String toString(){
        return this.getApellidos().concat(" ").concat(this.getNombres());
    }

    @Id
    @Column(name="instructor_id")
    public String getInstructorId(){
        return this.instructorId.get();
    }
    public void setInstructorId(String instructorId){
        this.instructorId.set(instructorId);
    }
    public StringProperty instructorId(){
        return this.instructorId;
    }

    @Column(name="apellidos")
    public String getApellidos(){
        return this.apellidos.get();
    }
    public void setApellidos(String apellidos){
        this.apellidos.set(apellidos);
    }
    public StringProperty apellidos (){
        return this.apellidos;
    }

    @Column(name="comentario")
    public String getComentario (){
        return this.comentario.get();
    }
    public void setComentario (String comentario){
        this.comentario.set(comentario);
    }
    public StringProperty comentario (){
        return this.comentario;
    }

    @Column(name="direccion")
    public String getDireccion(){
        return this.direccion.get();
    }
    public void setDireccion(String direccion){
        this.direccion.set(direccion);
    }
    public StringProperty direccion(){
        return this.direccion;
    }

    @Column(name="estatus")
    public String getStatus(){
        return this.status.get();
    }
    public void setStatus (String status){
        this.status.set(status);
    }
    public StringProperty status (){
        return this.status;
    }

    @Column(name="foto")
    public String getFoto(){
        return this.foto.get();
    }
    public void setFoto(String foto){
        this.foto.set(foto);
    }
    public StringProperty foto(){
        return this.foto;
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

    @Column(name="telefono")
    public String getTelefono(){
        return this.telefono.get();
    }
    public void setTelefono(String telefono){
        this.telefono.set(telefono);
    }
    public StringProperty telefono(){
        return this.telefono;
    }

    @OneToMany (mappedBy = "instructor", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}
