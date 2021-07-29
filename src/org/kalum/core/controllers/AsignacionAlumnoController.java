package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.AsignacionAlumno;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AsignacionAlumnoController implements Initializable {
    @FXML private TableView<AsignacionAlumno> tblAsignacionAlumno;
    @FXML private TableColumn<AsignacionAlumno,String> colCarne;
    @FXML private TableColumn<AsignacionAlumno,String> colClase;
    @FXML private TableColumn<AsignacionAlumno,String> colFechaAsignacion;

    private Main escenarioPrincipal;
    private ObservableList<AsignacionAlumno> listaAsignaciones;
    private AsignacionAlumno asignacionAlumno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.listaAsignaciones = FXCollections.observableArrayList
                    ((List<AsignacionAlumno>) ConexionPU.getInstacia().findAll("AsignacionAlumno.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de asignaciones"
            ,e.getMessage(),50302);
        }
        this.tblAsignacionAlumno.setItems(this.listaAsignaciones);
        this.colCarne.setCellValueFactory(cellData -> cellData.getValue().getAlumno().carne());
        this.colFechaAsignacion.setCellValueFactory(cellData -> cellData.getValue().fechaAsignacion().asString());
        this.colClase.setCellValueFactory(cellData -> cellData.getValue().getClase().descripcion());
    }
    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void ventanaAsignacionCU(){
        this.escenarioPrincipal.mostrarVentanaAsignacionAlumnoCU();
    }

    public void modificar(){
        if(this.tblAsignacionAlumno.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de asignaciones");
            aviso.setContentText("Debe de seleccionar un registro!");
            aviso.show();
        }else{
            this.escenarioPrincipal.mostrarVentanaAsignacionAlumnoCU(this.tblAsignacionAlumno.getSelectionModel().getSelectedItem());
        }
    }

    public void eliminar(){
        if(this.tblAsignacionAlumno.getSelectionModel().getSelectedItem()== null ){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de asignaciones");
            aviso.setContentText("Debe de seleccionar un registro");
            aviso.show();
        }else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Alerta!!!!");
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            Optional<ButtonType> result = aviso.showAndWait();
            if(result.get()== ButtonType.OK){
                int posicion = this.tblAsignacionAlumno.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstacia().eliminar(this.tblAsignacionAlumno.getSelectionModel().getSelectedItem());
                this.listaAsignaciones.remove(posicion);
            }
        }
    }
}
