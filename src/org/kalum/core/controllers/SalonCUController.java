package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Salon;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class SalonCUController implements Initializable {
    @FXML private TextField txtCapacidad;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtNombre;

    private Main escenarioPrincipal;
    private Salon salon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        try {
            if (salon == null) {
                Salon salon = new Salon();
                salon.setSalonId(UUID.randomUUID().toString());
                salon.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                salon.setDescripcion(txtDescripcion.getText());
                salon.setNombreSalon(txtNombre.getText());
                ConexionPU.getInstacia().agregar(salon);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de salones");
                aviso.setContentText("¡Registro almacenado exitosamente!");
                aviso.show();
            } else {
                salon.setCapacidad(Integer.parseInt((txtCapacidad.getText())));
                salon.setDescripcion(txtDescripcion.getText());
                salon.setNombreSalon(txtNombre.getText());
                ConexionPU.getInstacia().modificar(salon);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de salones");
                aviso.setContentText("¡Registro Modificado exitosamente!");
                aviso.show();
            }

            this.escenarioPrincipal.mostrarEscenaSalon();
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de salones",e.getMessage(),50301);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;

    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaSalon();
    }

    public void setSalon (Salon salon){
        this.salon = salon;
        this.txtCapacidad.setText(String.valueOf(salon.getCapacidad()));
        this.txtDescripcion.setText(salon.getDescripcion());
        this.txtNombre.setText(salon.getNombreSalon());
    }
}
