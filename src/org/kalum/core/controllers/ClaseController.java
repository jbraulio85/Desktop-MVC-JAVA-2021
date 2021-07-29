package org.kalum.core.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Clase;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class ClaseController implements Initializable {
    @FXML private TableView<Clase> tblClase;
    @FXML private TableColumn<Clase,String> colDescripcion;
    @FXML private TableColumn<Clase,Number> colCiclo;
    @FXML private TableColumn<Clase,String> colCarrera;
    @FXML private TableColumn<Clase,String> colHorario;
    @FXML private TableColumn<Clase,String> colInstructor;
    @FXML private TableColumn<Clase,String> colSalon;
    @FXML private TableColumn<Clase,String> colCupo;

    private ObservableList<Clase> listaClases;
    private Main escenarioPrincipal;
    private Clase clase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.listaClases = FXCollections.observableArrayList
                    ((List<Clase>) ConexionPU.getInstacia().findAll("Clase.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de clases",e.getMessage(),50302);
        }
        this.tblClase.setItems(this.listaClases);
        this.colDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcion());
        this.colCiclo.setCellValueFactory(cellData -> cellData.getValue().ciclo());
        this.colCarrera.setCellValueFactory(cellData -> cellData.getValue().getCarreraTecnica().nombreCarrera());
        this.colHorario.setCellValueFactory(cellData -> new ReadOnlyStringWrapper
                (cellData.getValue().getHorario().horarioInicio() + " " + "-" + " " +
                cellData.getValue().getHorario().horarioFinal()));
        this.colInstructor.setCellValueFactory(cellData -> new ReadOnlyStringWrapper
                ( cellData.getValue().getInstructor().nombres() + " " +
                cellData.getValue().getInstructor().apellidos()));
        this.colSalon.setCellValueFactory(cellData -> cellData.getValue().getSalon().nombreSalon());
        this.colCupo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper
                (cellData.getValue().cupoMinimo()+" "+ "-" + " " +
                cellData.getValue().cupoMaximo()));
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarVentanaClaseCU(){
        this.escenarioPrincipal.mostrarEscenaClaseCU();
    }

    public void modificar(){
        if(this.tblClase.getSelectionModel().getSelectedItem() == null)
        {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de clases");
            aviso.setContentText("Debe de seleccionar un registro!");
            aviso.show();
        }else{
            this.escenarioPrincipal.mostrarEscenaClaseCU(this.tblClase.getSelectionModel().getSelectedItem());
        }
    }
    public void eliminar(){
        if(this.tblClase.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de clases");
            aviso.setContentText("Debe de seleccionar un registro");
            aviso.show();
        }else{
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Alerta!!!!");
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            Optional<ButtonType> result = aviso.showAndWait();
            if(result.get() == ButtonType.OK){
                int posicion = this.tblClase.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstacia().eliminar(this.tblClase.getSelectionModel().getSelectedItem());
                this.listaClases.remove(posicion);
            }
        }
    }
    public void generarReporte(){
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de clases","ReporteClases.jasper",parametros);
    }
}
