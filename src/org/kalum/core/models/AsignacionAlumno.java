package org.kalum.core.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name ="asignacion_alumno")
@NamedQueries({@NamedQuery(name ="AsignacionAlumno.findAll",query = "from AsignacionAlumno")})
public class AsignacionAlumno implements Serializable {
    private final StringProperty asignacionId;
    private final ObjectProperty<LocalDate> fechaAsignacion;
    private Clase clase;
    private Alumno alumno;

    public AsignacionAlumno(){
        this.asignacionId = new SimpleStringProperty();
        this.fechaAsignacion = new SimpleObjectProperty<LocalDate>();
    }

    @Id
    @Column(name ="asignacion_id")
    public String getAsignacionId() {
        return asignacionId.get();
    }

    public StringProperty asignacionId() {
        return asignacionId;
    }

    public void setAsignacionId(String asignacionId) {
        this.asignacionId.set(asignacionId);
    }

    @Column(name="fecha_asignacion")
    public LocalDate getFechaAsignacion() {
        return fechaAsignacion.get();
    }

    public ObjectProperty<LocalDate> fechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion.set(fechaAsignacion);
    }

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name="clase_id")
    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name="carne")
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
