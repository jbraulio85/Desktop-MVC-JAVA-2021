package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Modulo;
import org.kalum.core.models.Seminario;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class SeminarioCUController implements Initializable {
    private Main escenarioPrincipal;
    private Seminario seminario;
    private ObservableList<Modulo> modulos;

    @FXML private ComboBox<Modulo> cmbModulo;
    @FXML private TextField txtNombre;
    @FXML private DatePicker dtFechaInicio;
    @FXML private DatePicker dtFechaFinal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.modulos = FXCollections.observableArrayList((List<Modulo>)
                            ConexionPU.getInstacia().findAll("Modulo.findAll"));
            this.cmbModulo.setItems(modulos);
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de modulos",e.getMessage(),50301);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaSeminario();
    }

    public void setSeminario(Seminario seminario) {
        this.seminario = seminario;
        this.cmbModulo.getSelectionModel().select(seminario.getModulo());
        this.txtNombre.setText(seminario.getNombreSeminario());
        this.dtFechaInicio.setValue(seminario.getFechaInicio());
        this.dtFechaFinal.setValue(seminario.getFechaFinal());
    }

    public void guardar(){
        try {
            if(this.seminario == null){
                Seminario nuevoSeminario = new Seminario();
                nuevoSeminario.setSeminarioId(UUID.randomUUID().toString());
                nuevoSeminario.setModulo(this.cmbModulo.getSelectionModel().getSelectedItem());
                nuevoSeminario.setFechaInicio(this.dtFechaInicio.getValue());
                nuevoSeminario.setFechaFinal(this.dtFechaFinal.getValue());
                nuevoSeminario.setNombreSeminario(this.txtNombre.getText());
                ConexionPU.getInstacia().agregar(nuevoSeminario);
            }else if(this.seminario != null){
                this.seminario.setModulo(this.cmbModulo.getSelectionModel().getSelectedItem());
                this.seminario.setNombreSeminario(this.txtNombre.getText());
                this.seminario.setFechaInicio(this.dtFechaInicio.getValue());
                this.seminario.setFechaFinal(this.dtFechaFinal.getValue());
                ConexionPU.getInstacia().modificar(this.seminario);
            }
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de seminarios",e.getMessage(),50301);
        }
        Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
        aviso.setTitle("Kalum v1.0.0");
        aviso.setHeaderText("Administración de seminarios");
        aviso.setContentText("¡Registro almacenado exitosamente!");
        aviso.show();
    }
}
