package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.converter.LocalDateStringConverter;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.AsignacionAlumno;
import org.kalum.core.models.Clase;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class AsignacionAlumnoCUController implements Initializable {
    private AsignacionAlumno asignacionAlumno;
    private Main escenarioPrincipal;

    private ObservableList<Clase> clases;
    private ObservableList<Alumno> alumnos;

    @FXML private ComboBox<Alumno> cmbAlumno;
    @FXML private DatePicker dtFecha;
    @FXML private ComboBox<Clase> cmbClase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.alumnos = FXCollections.observableArrayList((List<Alumno>) ConexionPU
                            .getInstacia()
                            .findAll("Alumno.findAll"));
            this.cmbAlumno.setItems(this.alumnos);
            this.clases = FXCollections.observableArrayList((List<Clase>) ConexionPU
                            .getInstacia()
                            .findAll("Clase.findAll"));
            this.cmbClase.setItems(this.clases);
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje
                    ("Administración de asignaciones",e.getMessage(),50302);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarVentanaAsignacionAlumno();
    }

    public void setAsignacionAlumno(AsignacionAlumno asignacionAlumno){
        this.asignacionAlumno = asignacionAlumno;
        this.cmbAlumno.getSelectionModel().select(asignacionAlumno.getAlumno());
        this.dtFecha.setValue(asignacionAlumno.getFechaAsignacion());
        this.cmbClase.getSelectionModel().select(asignacionAlumno.getClase());
    }

    public void guardar(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
        try{
            if(this.asignacionAlumno == null){
                AsignacionAlumno nuevaAsignacion = new AsignacionAlumno();
                nuevaAsignacion.setAsignacionId(UUID.randomUUID().toString());
                nuevaAsignacion.setAlumno(this.cmbAlumno.getSelectionModel().getSelectedItem());
                nuevaAsignacion.setClase(this.cmbClase.getSelectionModel().getSelectedItem());
                nuevaAsignacion.setFechaAsignacion(this.dtFecha.getValue());
                ConexionPU.getInstacia().agregar(nuevaAsignacion);
            } else if (this.asignacionAlumno != null) {
                this.asignacionAlumno.setAlumno(this.cmbAlumno.getSelectionModel().getSelectedItem());
                this.asignacionAlumno.setFechaAsignacion(this.dtFecha.getValue());
                this.asignacionAlumno.setClase(this.cmbClase.getSelectionModel().getSelectedItem());
            }
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de asignaciones",
                    e.getMessage(),50301);
        }
        Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
        aviso.setTitle("Kalum v1.0.0");
        aviso.setHeaderText("Administración de horarios");
        aviso.setContentText("¡Registro almacenado exitosamente!");
        aviso.show();
    }
}
