package org.kalum.core.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.models.Horario;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class HorarioController implements Initializable {
    @FXML private TableView<Horario> tblHorarios;
    @FXML private TableColumn<Horario, String> colHorarios;

    private ObservableList<Horario> listaHorarios;
    private Main escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TimeZone.setDefault( TimeZone.getTimeZone("UTC"));
        SimpleDateFormat tsdf = new SimpleDateFormat("HH:mm");
        try{
            this.listaHorarios = FXCollections.observableArrayList
                    ((List<Horario>) ConexionPU.getInstacia().findAll("Horario.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje
                    ("Administración de carreras",e.getMessage(),503002);
        }

        this.tblHorarios.setItems(this.listaHorarios);
        this.colHorarios.setCellValueFactory(cellData -> new ReadOnlyStringWrapper
                (tsdf.format( cellData.getValue().getHorarioInicio()) + " - " + tsdf.format( cellData.getValue().getHorarioFinal())));
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void nuevo(){
        this.escenarioPrincipal.mostrarEscenaHorarioCU();
    }

    public void eliminar(){
        if(this.tblHorarios.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de horarios");
            aviso.setContentText("Debe de seleccionar un registro");
            aviso.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Kalum v1.0.0");
            alert.setHeaderText("Alerta!!!!");
            alert.setContentText("¿Está seguro de eliminar el registro");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK){
                int posicion = this.tblHorarios.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstacia().eliminar(this.tblHorarios.getSelectionModel().getSelectedItem());
                this.listaHorarios.remove(posicion);
            }
        }
    }

    public void modificar(){
        if(this.tblHorarios.getSelectionModel().getSelectedItem() ==  null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de horarios");
            aviso.setContentText("Debe de seleccionar un registro!");
            aviso.show();
        }else{
            this.escenarioPrincipal.mostrarEscenaHorarioCU(this.tblHorarios.getSelectionModel().getSelectedItem());
        }
    }

}
