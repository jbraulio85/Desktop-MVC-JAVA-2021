package org.kalum.core.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="carrera_tecnica")
@NamedQueries({@NamedQuery(name="CarreraTecnica.findAll", query ="from CarreraTecnica")})
public class CarreraTecnica implements Serializable {

    private final StringProperty codigoCarrera;
    private final StringProperty nombreCarrera;
    private List<Clase> clases;
    private List<Modulo> modulos;

    public CarreraTecnica(){
        this.codigoCarrera = new SimpleStringProperty();
        this.nombreCarrera = new SimpleStringProperty();
    }

    @Override
    public String toString(){
        return this.getNombre();
    }

    @Id
    @Column(name="codigo_carrera")
    public String getCodigoCarrera(){
        return this.codigoCarrera.get();
    }
    public void setCodigoCarrera (String codigoCarrera){
        this.codigoCarrera.set(codigoCarrera);
    }
    public StringProperty codigoCarrera(){
        return this.codigoCarrera;
    }

    @Column(name="nombre")
    public String getNombre(){
        return this.nombreCarrera.get();
    }
    public void setNombre (String nombreCarrera){
        this.nombreCarrera.set(nombreCarrera);
    }
    public StringProperty nombreCarrera (){
        return this.nombreCarrera;
    }

    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.LAZY)
    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }
}
