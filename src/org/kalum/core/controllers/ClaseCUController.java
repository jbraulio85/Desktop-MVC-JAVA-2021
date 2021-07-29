package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.models.Horario;
import org.kalum.core.models.Instructor;
import org.kalum.core.models.Salon;
import org.kalum.core.models.Clase;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class ClaseCUController implements Initializable {
    private Main escenarioPrincipal;
    private Clase clase;

    private SpinnerValueFactory<Integer> valueFactoryCiclo = new SpinnerValueFactory.IntegerSpinnerValueFactory
            (2020,2040,2021);
    private SpinnerValueFactory<Integer> valueFactoryMaximo = new SpinnerValueFactory.IntegerSpinnerValueFactory
            (1,30,1);
    private SpinnerValueFactory<Integer> valueFactoryMinimo = new SpinnerValueFactory.IntegerSpinnerValueFactory
            (1,5,1);

    private ObservableList<Instructor> instructores;
    private ObservableList<Horario> horarios;
    private ObservableList<Salon> salones;
    private ObservableList<CarreraTecnica> carreras;

    @FXML private TextField txtDescripcion;
    @FXML private Spinner<Integer> numCiclo;
    @FXML private Spinner<Integer> numMaximo;
    @FXML private Spinner<Integer> numMinimo;
    @FXML private ComboBox<CarreraTecnica> cmbCarrera;
    @FXML private ComboBox<Horario> cmbHorario;
    @FXML private ComboBox<Instructor> cmbInstructor;
    @FXML private ComboBox<Salon> cmbSalon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numCiclo.setValueFactory(this.valueFactoryCiclo);
        this.numMaximo.setValueFactory(this.valueFactoryMaximo);
        this.numMinimo.setValueFactory(this.valueFactoryMinimo);
        try{
            this.instructores = FXCollections.observableArrayList((List<Instructor>)ConexionPU
                    .getInstacia()
                    .findAll("Instructor.findAll"));
            this.cmbInstructor.setItems(this.instructores);
            this.horarios = FXCollections.observableArrayList((List<Horario>)ConexionPU
                    .getInstacia()
                    .findAll("Horario.findAll"));
            this.cmbHorario.setItems(this.horarios);
            this.salones = FXCollections.observableArrayList((List<Salon>)ConexionPU
                    .getInstacia()
                    .findAll("Salon.findAll"));
            this.cmbSalon.setItems(this.salones);
            this.carreras = FXCollections.observableArrayList((List<CarreraTecnica>)ConexionPU
                    .getInstacia()
                    .findAll("CarreraTecnica.findAll"));
            this.cmbCarrera.setItems(this.carreras);
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje
                    ("Administración de carreras",e.getMessage(),50302);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaClase();
    }

    public void setClase(Clase clase) {
        this.clase = clase;
        this.txtDescripcion.setText(clase.getDescripcion());
        this.valueFactoryCiclo.setValue(clase.getCiclo());
        this.valueFactoryMaximo.setValue(clase.getCupoMaximo());
        this.valueFactoryMinimo.setValue(clase.getCupoMinimo());
        this.cmbCarrera.getSelectionModel().select(clase.getCarreraTecnica());
        this.cmbHorario.getSelectionModel().select(clase.getHorario());
        this.cmbInstructor.getSelectionModel().select(clase.getInstructor());
        this.cmbSalon.getSelectionModel().select(clase.getSalon());
    }

    public void guardar() {
        try {
            if (this.clase == null) {
                Clase nuevaClase = new Clase();
                nuevaClase.setClaseId(UUID.randomUUID().toString());
                nuevaClase.setDescripcion(this.txtDescripcion.getText());
                nuevaClase.setCiclo(this.numCiclo.getValue());
                nuevaClase.setCupoMaximo(this.numMaximo.getValue());
                nuevaClase.setCupoMinimo(this.numMinimo.getValue());
                nuevaClase.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                nuevaClase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                nuevaClase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                nuevaClase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());
                ConexionPU.getInstacia().agregar(nuevaClase);
            } else if (this.clase != null) {
                this.clase.setDescripcion(txtDescripcion.getText());
                this.clase.setCiclo(numCiclo.getValue());
                this.clase.setCupoMaximo(numMaximo.getValue());
                this.clase.setCupoMinimo(numMinimo.getValue());
                this.clase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());
                this.clase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                this.clase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                this.clase.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                ConexionPU.getInstacia().modificar(this.clase);
            }
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de clases");
            aviso.setContentText("Regristro almacenado correctamente!");
            aviso.show();
            this.escenarioPrincipal.mostrarEscenaClase();
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administradción de clases", e.getMessage(), 50301);
        }
    }
}
