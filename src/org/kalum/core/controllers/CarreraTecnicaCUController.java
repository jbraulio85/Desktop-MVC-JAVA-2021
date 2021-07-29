package org.kalum.core.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class CarreraTecnicaCUController implements Initializable {
    @FXML private JFXTextField txtCarrera;

    private Main escenarioPrincipal;
    private CarreraTecnica carreraTecnica;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        try {
            if (carreraTecnica == null) {
                CarreraTecnica carreraTecnica = new CarreraTecnica();
                carreraTecnica.setCodigoCarrera(UUID.randomUUID().toString());
                carreraTecnica.setNombre(txtCarrera.getText());
                ConexionPU.getInstacia().agregar(carreraTecnica);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de Carrera");
                aviso.setContentText("¡Registro almacenado exitosamente!");
                aviso.show();
            } else {
                carreraTecnica.setNombre(txtCarrera.getText());
                ConexionPU.getInstacia().modificar(carreraTecnica);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("¡Kalum v1.0.0");
                aviso.setHeaderText("Administración de Carrera");
                aviso.setContentText("¡Registro modificado exitosamente!");
                aviso.show();
            }
            this.escenarioPrincipal.mostrarEscenaCarreraTecnica();
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de carrera",e.getMessage(),50301);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal){
        this.escenarioPrincipal = escenarioPrincipal;
    }
    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaCarreraTecnica();
    }

    public CarreraTecnica getCarreraTecnica(){
        return carreraTecnica;
    }
    public void setCarreraTecnica(CarreraTecnica carreraTecnica){
        this.carreraTecnica = carreraTecnica;
        this.txtCarrera.setText(carreraTecnica.getNombre());
    }
}
