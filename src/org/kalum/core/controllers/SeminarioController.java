package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Seminario;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SeminarioController implements Initializable {
    private Main escenarioPrincipal;
    private ObservableList<Seminario> listaSeminarios;
    private Seminario seminario;

    @FXML private TableView<Seminario> tblSeminario;
    @FXML private TableColumn<Seminario,String> colSeminario;
    @FXML private TableColumn<Seminario,String> colModulo;
    @FXML private TableColumn<Seminario,String> colFechaInicio;
    @FXML private TableColumn<Seminario,String> colFechaFinal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.listaSeminarios = FXCollections.observableArrayList
                    ((List<Seminario>) ConexionPU.getInstacia().findAll("Seminario.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de seminarios",e.getMessage(),50302);
        }
        this.tblSeminario.setItems(listaSeminarios);
        this.colSeminario.setCellValueFactory(cellData -> cellData.getValue().nombreSeminario());
        this.colModulo.setCellValueFactory(cellData -> cellData.getValue().getModulo().nombreModulo());
        this.colFechaInicio.setCellValueFactory(cellData -> cellData.getValue().fechaInicio().asString());
        this.colFechaFinal.setCellValueFactory(cellData -> cellData.getValue().fechaFinal().asString());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void nuevo(){
        this.escenarioPrincipal.mostrarEscenaSeminarioCU();
    }

    public void modificar(){
        if(this.tblSeminario.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de seminarios");
            aviso.setContentText("Debe de seleccionar un registro!");
            aviso.show();
        }else{
            this.escenarioPrincipal.mostrarEscenaSeminarioCU(this.tblSeminario.getSelectionModel().getSelectedItem());
        }
    }
}
