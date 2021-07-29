package org.kalum.core.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="seminario")
@NamedQueries({@NamedQuery(name="Seminario.findAll",query = "from Seminario")})
public class Seminario implements Serializable {
    private final StringProperty seminarioId;
    private final StringProperty nombreSeminario;
    private final ObjectProperty<LocalDate> fechaInicio;
    private final ObjectProperty<LocalDate> fechaFinal;
    private Modulo modulo;

    public Seminario(){
        this.seminarioId = new SimpleStringProperty();
        this.nombreSeminario = new SimpleStringProperty();
        this.fechaInicio = new SimpleObjectProperty<LocalDate>();
        this.fechaFinal = new SimpleObjectProperty<LocalDate>();
    }

    @Id
    @Column(name="seminario_id")
    public String getSeminarioId() {
        return seminarioId.get();
    }

    public StringProperty seminarioId() {
        return seminarioId;
    }

    public void setSeminarioId(String seminarioId) {
        this.seminarioId.set(seminarioId);
    }

    @Column(name="nombre_seminario")
    public String getNombreSeminario() {
        return nombreSeminario.get();
    }

    public StringProperty nombreSeminario() {
        return nombreSeminario;
    }

    public void setNombreSeminario(String nombreSeminario) {
        this.nombreSeminario.set(nombreSeminario);
    }

    @Column(name="fecha_inicio")
    public LocalDate getFechaInicio() {
        return fechaInicio.get();
    }

    public ObjectProperty<LocalDate> fechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio.set(fechaInicio);
    }

    @Column(name="fecha_fin")
    public LocalDate getFechaFinal() {
        return fechaFinal.get();
    }

    public ObjectProperty<LocalDate> fechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal.set(fechaFinal);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name ="modulo_id")
    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
}
