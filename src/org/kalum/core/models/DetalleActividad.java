package org.kalum.core.models;

import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="detalle_actividad")
@NamedQueries({@NamedQuery(name="DetalleActividad.findAll",query = "from DetalleActividad")})
public class DetalleActividad implements Serializable {
    private final StringProperty detalleActividadId;
    private final StringProperty nombreActividad;
    private final IntegerProperty notaActividad;
    private final ObjectProperty<LocalDate> fechaCreacion;
    private final ObjectProperty<LocalDate> fechaEntrega;
    private final ObjectProperty<LocalDate> fechaPostergacion;
    private final StringProperty estado;

    private Seminario seminario;

    public DetalleActividad(){
        this.detalleActividadId = new SimpleStringProperty();
        this.nombreActividad = new SimpleStringProperty();
        this.notaActividad = new SimpleIntegerProperty();
        this.fechaCreacion = new SimpleObjectProperty<LocalDate>();
        this.fechaEntrega = new SimpleObjectProperty<LocalDate>();
        this.fechaPostergacion = new SimpleObjectProperty<LocalDate>();
        this.estado = new SimpleStringProperty();
    }

    @Id
    @Column(name="detalle_actividad_id")
    public String getDetalleActividadId() {
        return detalleActividadId.get();
    }

    public StringProperty detalleActividadId() {
        return detalleActividadId;
    }

    public void setDetalleActividadId(String detalleActividadId) {
        this.detalleActividadId.set(detalleActividadId);
    }

    @Column(name="nombre_actividad")
    public String getNombreActividad() {
        return nombreActividad.get();
    }

    public StringProperty nombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad.set(nombreActividad);
    }

    @Column(name="nota_actividad")
    public int getNotaActividad() {
        return notaActividad.get();
    }

    public IntegerProperty notaActividad() {
        return notaActividad;
    }

    public void setNotaActividad(int notaActividad) {
        this.notaActividad.set(notaActividad);
    }

    @Column(name="fecha_creacion")
    public LocalDate getFechaCreacion() {
        return fechaCreacion.get();
    }

    public ObjectProperty<LocalDate> fechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion.set(fechaCreacion);
    }

    @Column(name="fecha_entrega")
    public LocalDate getFechaEntrega() {
        return fechaEntrega.get();
    }

    public ObjectProperty<LocalDate> fechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega.set(fechaEntrega);
    }

    @Column(name="fecha_postergacion")
    public LocalDate getFechaPostergacion() {
        return fechaPostergacion.get();
    }

    public ObjectProperty<LocalDate> fechaPostergacion() {
        return fechaPostergacion;
    }

    public void setFechaPostergacion(LocalDate fechaPostergacion) {
        this.fechaPostergacion.set(fechaPostergacion);
    }

    @Column(name="estado")
    public String getEstado() {
        return estado.get();
    }

    public StringProperty estado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seminario_id")
    public Seminario getSeminario() {
        return seminario;
    }

    public void setSeminario(Seminario seminario) {
        this.seminario = seminario;
    }
}
