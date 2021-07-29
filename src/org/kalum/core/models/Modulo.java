package org.kalum.core.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name ="modulo")
@NamedQueries({@NamedQuery(name="Modulo.findAll",query="from Modulo")})
public class Modulo implements Serializable {
    private final StringProperty moduloId;
    private final StringProperty nombreModulo;
    private final IntegerProperty numeroSeminarios;
    private CarreraTecnica carreraTecnica;
    private List<Seminario> seminarios;

    public Modulo(){
        this.moduloId = new SimpleStringProperty();
        this.nombreModulo = new SimpleStringProperty();
        this.numeroSeminarios = new SimpleIntegerProperty();
    }

    @Override
    public String toString(){
        return this.getNombreModulo();
    }
    @Id
    @Column (name="modulo_id")
    public String getModuloId() {
        return moduloId.get();
    }

    public StringProperty moduloId() {
        return moduloId;
    }

    public void setModuloId(String moduloId) {
        this.moduloId.set(moduloId);
    }

    @Column(name="nombre_modulo")
    public String getNombreModulo() {
        return nombreModulo.get();
    }

    public StringProperty nombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo.set(nombreModulo);
    }

    @Column(name="numero_seminarios")
    public int getNumeroSeminarios() {
        return numeroSeminarios.get();
    }

    public IntegerProperty numeroSeminarios() {
        return numeroSeminarios;
    }

    public void setNumeroSeminarios(int numeroSeminarios) {
        this.numeroSeminarios.set(numeroSeminarios);
    }

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name="codigo_carrera")
    public CarreraTecnica getCarreraTecnica() {
        return carreraTecnica;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
    }

    @OneToMany (mappedBy = "modulo",fetch = FetchType.LAZY)
    public List<Seminario> getSeminarios() {
        return seminarios;
    }

    public void setSeminarios(List<Seminario> seminarios) {
        this.seminarios = seminarios;
    }
}
