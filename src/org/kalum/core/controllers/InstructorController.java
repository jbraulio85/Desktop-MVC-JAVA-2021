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
import org.kalum.core.models.Instructor;
import org.kalum.core.reports.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class InstructorController implements Initializable {
    @FXML private TableView<Instructor> tblInstructor;
    @FXML private TableColumn<Instructor,String> colId;
    @FXML private TableColumn<Instructor,String> colFoto;
    @FXML private TableColumn<Instructor,String> colApellidos;
    @FXML private TableColumn<Instructor,String> colNombres;
    @FXML private TableColumn<Instructor,String> colTelefono;
    @FXML private TableColumn<Instructor,String> colDireccion;
    @FXML private TableColumn<Instructor,String> colEstatus;
    @FXML private TableColumn<Instructor,String> colComentario;

    private ObservableList<Instructor> listaInstructores;
    private Main escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            this.listaInstructores = FXCollections.observableArrayList
                    ((List<Instructor>) ConexionPU.getInstacia().findAll("Instructor.findAll"));
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje
                    ("Administración de Instructores",e.getMessage(),503002);
        }
        this.tblInstructor.setItems(this.listaInstructores);
        this.colId.setCellValueFactory(cellData -> cellData.getValue().instructorId());
        this.colFoto.setCellValueFactory(cellData -> cellData.getValue().foto());
        this.colApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidos());
        this.colNombres.setCellValueFactory(cellData -> cellData.getValue().nombres());
        this.colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefono());
        this.colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccion());
        this.colEstatus.setCellValueFactory(cellDAta -> cellDAta.getValue().status());
        this.colComentario.setCellValueFactory(cellData -> cellData.getValue().comentario());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void mostrarVentanaInstructorCU(){
        this.escenarioPrincipal.mostrarEscenaInstructorCU();
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarVentanaInstructorUpdate(){
        if(this.tblInstructor.getSelectionModel().getSelectedItem() == null){
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de instructores");
            aviso.setContentText("Debe de seleccionar un registro");
            aviso.show();
        }else{
            this.escenarioPrincipal.mostrarEscenaInstructorCU(this.tblInstructor.getSelectionModel().getSelectedItem());
        }
    }

    public void eliminar (){
        if(this.tblInstructor.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de instructores");
            aviso.setContentText("Debe de seleccionar un registro");
            aviso.show();
        }else{
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de instructores");
            aviso.setContentText("¿Está seguro que desea eliminar el registro");
            aviso.show();
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK){
                int posicion = this.tblInstructor.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstacia().eliminar(this.tblInstructor.getSelectionModel().getSelectedItem());
                this.listaInstructores.remove(posicion);
            }
        }
    }

    public void generarReporte(){
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de instructores","ReporteInstructores.jasper",parametros);
    }
}
