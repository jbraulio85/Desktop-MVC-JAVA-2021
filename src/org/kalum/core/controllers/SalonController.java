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
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.models.Salon;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SalonController implements Initializable {
    @FXML private TableView<Salon> tblSalon;
    @FXML private TableColumn<Salon,Integer> colCapacidad;
    @FXML private TableColumn<Salon,String> colNombreSalon;
    @FXML private TableColumn<Salon,String> colDescripcion;

    private ObservableList<Salon> listaSalones;
    private Main escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.listaSalones = FXCollections.observableArrayList
                    ((List<Salon>) ConexionPU.getInstacia().findAll("Salon.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje
                    ("Administración de salones",e.getMessage(),503002);
        }
        this.tblSalon.setItems(this.listaSalones);
        this.colCapacidad.setCellValueFactory(cellData -> cellData.getValue().capacidad().asObject());
        this.colNombreSalon.setCellValueFactory(cellData -> cellData.getValue().nombreSalon());
        this.colDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcion());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void mostrarVentanaSalonCU(){
        this.escenarioPrincipal.mostrarEscenaSalonCU();
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarVentanaSalonUpdate(){
        if (this.tblSalon.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de salones");
            aviso.setContentText("Debe de seleccionar un registro");
            aviso.show();
        }else{
            this.escenarioPrincipal.mostrarEscenaSalonCU(this.tblSalon.getSelectionModel().getSelectedItem());
        }
    }

    public void eliminar(){
        if(this.tblSalon.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de salones");
            aviso.setContentText("debe de seleccionar un registro");
            aviso.show();
        }else{
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Alerta!!!");
            aviso.setContentText("¿Está seguro que desea eliminar el regitro?");
            Optional<ButtonType> result = aviso.showAndWait();
            if(result.get() == ButtonType.OK){
                int posicion = this.tblSalon.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstacia().eliminar(this.tblSalon.getSelectionModel().getSelectedItem());
                this.listaSalones.remove(posicion);
            }
        }
    }
}
