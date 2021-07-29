package org.kalum.core.controllers;

import javafx.fxml.Initializable;
import org.kalum.core.sistema.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Main escenarioPrincipal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ventanaAlumnos(){
        this.escenarioPrincipal.mostrarEscenaAlumnos();
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    public void ventanaInstructores(){
        this.escenarioPrincipal.mostrarEscenaInstructor();
    }

    public void ventanaSalones(){
        this.escenarioPrincipal.mostrarEscenaSalon();
    }

    public void ventanaCarrerasTecnicas(){
        this.escenarioPrincipal.mostrarEscenaCarreraTecnica();
    }

    public void ventanaHorarios(){
        this.escenarioPrincipal.mostrarEscenaHorario();
    }

    public void mostrarEscenaClase(){
        this.escenarioPrincipal.mostrarEscenaClase();
    }

    public void mostrarEscenaClaseCU(){
        this.escenarioPrincipal.mostrarEscenaClaseCU();
    }
    public void cerrarSesion(){
        this.escenarioPrincipal.mostrarVentanaLogin();
    }
    public void ventanaAsignacionAlumnos(){
        this.escenarioPrincipal.mostrarVentanaAsignacionAlumno();
    }

    public void ventanaModulos(){
        this.escenarioPrincipal.mostrarEscenaModulo();
    }

    public void ventanaSeminarios(){
        this.escenarioPrincipal.mostrarEscenaSeminario();
    }
}
