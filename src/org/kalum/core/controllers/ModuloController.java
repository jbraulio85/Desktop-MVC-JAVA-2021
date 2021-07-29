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
import org.kalum.core.models.Modulo;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModuloController implements Initializable {
    @FXML private TableView<Modulo> tblModulos;
    @FXML private TableColumn<Modulo,String> colCarrera;
    @FXML private TableColumn<Modulo,String> colNombreModulo;
    @FXML private TableColumn<Modulo,Number> colCantSeminarios;

    private ObservableList<Modulo> listaModulos;
    private Modulo modulo;
    private Main escenarioPrincipal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.listaModulos = FXCollections.observableArrayList
                    ((List<Modulo>) ConexionPU.getInstacia().findAll("Modulo.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de modulos",e.getMessage(),50302);
        }
        this.tblModulos.setItems(this.listaModulos);
        this.colCarrera.setCellValueFactory(cellData -> cellData.getValue().getCarreraTecnica().nombreCarrera());
        this.colNombreModulo.setCellValueFactory(cellData -> cellData.getValue().nombreModulo());
        this.colCantSeminarios.setCellValueFactory(cellData -> cellData.getValue().numeroSeminarios());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void nuevo(){
        this.escenarioPrincipal.mostrarEscenaModuloCU();
    }

    public void modificar(){
        if(this.tblModulos.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de modulos");
            aviso.setContentText("Debe de seleccionar un registro!");
            aviso.show();
        }else {
            this.escenarioPrincipal.mostrarEscenaModuloCU(this.tblModulos.getSelectionModel().getSelectedItem());
        }
    }

    public void eliminar(){
        if(this.tblModulos.getSelectionModel().getSelectedItem() ==  null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de modulos");
            aviso.setContentText("Debe de seleccionar un registro!");
            aviso.show();
        }else if(this.tblModulos.getSelectionModel().getSelectedItem() != null){
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de modulos");
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            Optional<ButtonType> result = aviso.showAndWait();
            if(result.get() == ButtonType.OK){
                int posicion = this.tblModulos.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstacia().eliminar(this.tblModulos.getSelectionModel().getSelectedItem());
                this.listaModulos.remove(posicion);
            }
        }
    }
}
