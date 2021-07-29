package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.nio.channels.ScatteringByteChannel;
import java.util.ResourceBundle;

public class AlumnoCUController implements Initializable {
    @FXML private TextField txtCarne;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtNombres;
    @FXML private TextField txtExpediente;
    @FXML private TextField txtEmail;

    private Main escenarioPrincipal;
    private Alumno alumno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        try {
            if (alumno == null) {
                Alumno alumno = new Alumno();
                alumno.setCarne(txtCarne.getText());
                alumno.setApellidos(txtApellidos.getText());
                alumno.setNombres(txtNombres.getText());
                alumno.setNoExpediente(txtExpediente.getText());
                alumno.setEmail(txtEmail.getText());
                ConexionPU.getInstacia().agregar(alumno);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de alumnos");
                aviso.setContentText("¡Registro almacenado exitosamente!");
                aviso.show();
            } else {
                alumno.setCarne(txtCarne.getText());
                alumno.setApellidos(txtApellidos.getText());
                alumno.setNombres(txtNombres.getText());
                alumno.setNoExpediente(txtExpediente.getText());
                alumno.setEmail(txtEmail.getText());
                ConexionPU.getInstacia().modificar(alumno);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de alumnos");
                aviso.setContentText("¡Registro Modificado exitosamente!");
                aviso.show();
            }
            this.escenarioPrincipal.mostrarEscenaAlumnos();
        }catch(KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Alumnos",e.getMessage(),50301);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaAlumnos();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.txtCarne.setEditable(false);
        this.txtCarne.setText(alumno.getCarne());
        this.txtApellidos.setText(alumno.getApellidos());
        this.txtNombres.setText(alumno.getNombres());
        this.txtExpediente.setText(alumno.getNoExpediente());
        this.txtEmail.setText(alumno.getEmail());
    }
}
