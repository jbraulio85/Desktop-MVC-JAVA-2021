package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Instructor;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructorCUController implements Initializable {
    @FXML private TextField txtId;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtComentario;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtEstatus;
    @FXML private TextField txtFoto;
    @FXML private TextField txtNombres;
    @FXML private TextField txtTelefono;

    private Main escenarioPrincipal;
    private Instructor instructor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void guardar() {
        try {
            if (instructor == null) {
                Instructor instructor = new Instructor();
                instructor.setInstructorId(txtId.getText());
                instructor.setApellidos(txtApellidos.getText());
                instructor.setComentario(txtComentario.getText());
                instructor.setDireccion(txtDireccion.getText());
                instructor.setStatus(txtEstatus.getText());
                instructor.setFoto(txtFoto.getText());
                instructor.setNombres(txtNombres.getText());
                instructor.setTelefono(txtTelefono.getText());
                ConexionPU.getInstacia().agregar(instructor);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de instructores");
                aviso.setContentText("¡Registro almacenado exitosamente!");
                aviso.show();
            } else {
                instructor.setInstructorId(txtId.getText());
                instructor.setApellidos(txtApellidos.getText());
                instructor.setComentario(txtComentario.getText());
                instructor.setDireccion(txtDireccion.getText());
                instructor.setStatus(txtEstatus.getText());
                instructor.setFoto(txtFoto.getText());
                instructor.setNombres(txtNombres.getText());
                instructor.setTelefono(txtTelefono.getText());
                ConexionPU.getInstacia().modificar(instructor);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de instructores");
                aviso.setContentText("¡Registro modificado exitosamente!");
                aviso.show();
            }
            this.escenarioPrincipal.mostrarEscenaInstructor();
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de instructores",e.getMessage(),50301);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaInstructor();
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor){
        this.instructor = instructor;
        this.txtId.setEditable(false);
        this.txtId.setText(instructor.getInstructorId());
        this.txtApellidos.setText(instructor.getApellidos());
        this.txtNombres.setText(instructor.getNombres());
        this.txtComentario.setText(instructor.getComentario());
        this.txtDireccion.setText(instructor.getDireccion());
        this.txtEstatus.setText(instructor.getStatus());
        this.txtFoto.setText(instructor.getFoto());
        this.txtTelefono.setText(instructor.getTelefono());
    }
}
