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
import org.kalum.core.models.Alumno;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class CarreraTecnicaController implements Initializable {
    @FXML private TableView<CarreraTecnica> tblCarreraTecnica;
    @FXML private TableColumn<CarreraTecnica,String> colCarreraTecnica;

    private ObservableList<CarreraTecnica> listaCarreras;
    private Main escenarioPrincipal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.listaCarreras = FXCollections.observableArrayList
                    ((List<CarreraTecnica>) ConexionPU.getInstacia().findAll("CarreraTecnica.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje
                    ("Administración de carreras",e.getMessage(),503002);
        }
        this.tblCarreraTecnica.setItems(this.listaCarreras);
        this.colCarreraTecnica.setCellValueFactory(cellData -> cellData.getValue().nombreCarrera());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarVentanaCarreraTecnicaCU(){
        this.escenarioPrincipal.mostrarEscenaCarreraTecnicaCU();
    }

    public void mostrarVentanaCarreraTecnicaUpdate(){
        if(this.tblCarreraTecnica.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de carreras");
            aviso.setContentText("Debe de seleccionar un elemento");
            aviso.show();
        }else{
            this.escenarioPrincipal.mostrarEscenaCarreraTecnicaCU(this.tblCarreraTecnica.getSelectionModel().getSelectedItem());
        }
    }

    public void eliminarCarrera(){
        if(this.tblCarreraTecnica.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de carreras");
            aviso.setContentText("Debe de seleccionar un elemento");
            aviso.show();
        }else{
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Alerta!!!!!!");
            aviso.setContentText("¿Está seguro que desea eleminar el registro?");
            Optional<ButtonType> result = aviso.showAndWait();
            if(result.get() == ButtonType.OK){
                int posicion = this.tblCarreraTecnica.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstacia().eliminar(this.tblCarreraTecnica.getSelectionModel().getSelectedItem());
                this.listaCarreras.remove(posicion);
            }
        }
    }

    public void generarReporte(){
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de carreras","ReporteCarreras.jasper",parametros);
    }
}
