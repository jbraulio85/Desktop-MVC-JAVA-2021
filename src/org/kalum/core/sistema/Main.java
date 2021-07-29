package org.kalum.core.sistema;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.kalum.core.controllers.*;
import org.kalum.core.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;


public class Main extends Application {

    private final String PAQUETE_VIEW = "../views/";
    private Stage escenarioPrincipal;

    @Override
    public void start(Stage escenarioPrincipal) throws Exception{

        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Kalum v1.0.0");
        //this.escenarioPrincipal.initStyle(StageStyle.UNDECORATED);
        this.mostrarVentanaLogin();
        this.escenarioPrincipal.show();
    }

    public void mostrarEscenaAlumnos(){
        try{
            AlumnoController controlador = (AlumnoController)this.cambiarEscena("AlumnoView.fxml", 696,400);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaPrincipal(){
        try {
            MainController controlador = (MainController)this.cambiarEscena("MainView.fxml",700,400);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void mostrarEscenaAlumnoCU(){
        try{
           AlumnoCUController controlador = (AlumnoCUController)this.cambiarEscena("AlumnoCUView.fxml",511,335);
           controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaAlumnoCU(Alumno elemento){
        try{
            AlumnoCUController controlador = (AlumnoCUController)this.cambiarEscena("AlumnoCUView.fxml",511,335);
            controlador.setAlumno(elemento);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaInstructor(){
        try{
            InstructorController controlador = (InstructorController)this.cambiarEscena("InstructorView.fxml",886,546);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaInstructorCU(){
        try {
            InstructorCUController controlador = (InstructorCUController)this.cambiarEscena("InstructorCUView.fxml",637,341);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaInstructorCU(Instructor elemento){
        try {
            InstructorCUController controlador = (InstructorCUController)this.cambiarEscena("InstructorCUView.fxml",637,341);
            controlador.setInstructor(elemento);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSalon(){
        try {
            SalonController controlador = (SalonController)this.cambiarEscena("SalonView.fxml",600,400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSalonCU(){
        try {
            SalonCUController controlador = (SalonCUController)this.cambiarEscena("SalonCUView.fxml",488,247);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSalonCU(Salon elemento){
        try {
            SalonCUController controlador = (SalonCUController)this.cambiarEscena("SalonCUView.fxml",488,247);
            controlador.setSalon(elemento);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaCarreraTecnica(){
        try{
            CarreraTecnicaController controlador = (CarreraTecnicaController)this.cambiarEscena("CarreraTecnicaView.fxml",537,348);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaCarreraTecnicaCU(){
        try{
           CarreraTecnicaCUController controlador = (CarreraTecnicaCUController)this.cambiarEscena("CarreraTecnicaCUView.fxml",386,201);
           controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaCarreraTecnicaCU(CarreraTecnica elemento){
        try{
            CarreraTecnicaCUController controlador = (CarreraTecnicaCUController)this.cambiarEscena("CarreraTecnicaCUView.fxml",386,201);
            controlador.setCarreraTecnica(elemento);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaHorario(){
        try{
            HorarioController controlador = (HorarioController) this.cambiarEscena("HorarioView.fxml",308,365);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaHorarioCU(){
        try{
            HorarioCUController controlador = (HorarioCUController) this.cambiarEscena("HorarioCUView.fxml",350,298);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaHorarioCU(Horario elemento){
        try{
            HorarioCUController controlador = (HorarioCUController) this.cambiarEscena("HorarioCUView.fxml",350,298);
            controlador.setEscenarioPrincipal(this);
            controlador.setHorario(elemento);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaClase(){
        try{
            ClaseController controlador = (ClaseController) this.cambiarEscena("ClaseView.fxml",871,400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaClaseCU(){
        try{
            ClaseCUController controlador = (ClaseCUController) this.cambiarEscena("ClasesCUView.fxml",600,442);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void mostrarEscenaClaseCU(Clase elemento){
        try{
            ClaseCUController controlador = (ClaseCUController) this.cambiarEscena("ClasesCUView.fxml",600,442);
            controlador.setEscenarioPrincipal(this);
            controlador.setClase(elemento);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarVentanaLogin(){
        try{
            LoginController controlador = (LoginController)this.cambiarEscena("LoginView.fxml",421,250);
            controlador.setEscenarioPrincipal(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAsignacionAlumno(){
        try{
            AsignacionAlumnoController controlador = (AsignacionAlumnoController) this.cambiarEscena("AsignacionAlumnoView.fxml",600,430);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAsignacionAlumnoCU(AsignacionAlumno elemento){
        try{
            AsignacionAlumnoCUController controlador = (AsignacionAlumnoCUController) this.cambiarEscena("AsignacionAlumnoCUView.fxml",488,347);
            controlador.setEscenarioPrincipal(this);
            controlador.setAsignacionAlumno(elemento);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAsignacionAlumnoCU(){
        try{
            AsignacionAlumnoCUController controlador = (AsignacionAlumnoCUController) this.cambiarEscena("AsignacionAlumnoCUView.fxml",488,347);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaModulo(){
        try{
            ModuloController controlador = (ModuloController) this.cambiarEscena("ModuloView.fxml",731,430);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaModuloCU(){
        try {
            ModuloCUController controlador = (ModuloCUController) this.cambiarEscena("ModuloCUView.fxml",493,349);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaModuloCU(Modulo elemento){
        try {
            ModuloCUController controlador = (ModuloCUController) this.cambiarEscena("ModuloCUView.fxml",493,349);
            controlador.setModulo(elemento);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSeminario(){
        try {
            SeminarioController controlador = (SeminarioController) this.cambiarEscena("SeminarioView.fxml",600,400);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSeminarioCU(){
        try{
            SeminarioCUController controlador = (SeminarioCUController) this.cambiarEscena("SeminarioCUView.fxml",600,400);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSeminarioCU(Seminario elemento){
        try{
            SeminarioCUController controlador = (SeminarioCUController) this.cambiarEscena("SeminarioCUView.fxml",600,400);
            controlador.setSeminario(elemento);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaDetalleActividad(){
        try{
            DetalleActividadController controlador = (DetalleActividadController) this.cambiarEscena("DetalleActividadView.fxml",600,400);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /*public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException {
       Initializable resultado = null;
        //Cargador de archivo FXML
        FXMLLoader cargadorFXML = new FXMLLoader();
        //Asignación lógica del archivo
        InputStream archivo = Main.class.getResourceAsStream(this.PAQUETE_VIEW+escena);
        //Cargador de fábrica para cargar el archivo FXML
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        //Ubicación del archivo FXML que se pinta en el escenario
        cargadorFXML.setLocation(PrintStream.class.getResource(this.PAQUETE_VIEW));
        //Creación de la escena
        Scene nuevaEscena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        nuevaEscena.getStylesheets().add("org/kalum/core/resources/styles/estilo.css");
        //Mostrar la escena
        this.escenarioPrincipal.setScene(nuevaEscena);
        //Ajustar el tamaño del escenario a la escena que se desea mostrar
        this.escenarioPrincipal.sizeToScene();
        //Obtener controlador del archivo FXML
        resultado = (Initializable) cargadorFXML.getController();
        //Retornar el controlador de la vista que se está mostrando
        return resultado;
    }*/

    public Initializable cambiarEscena (String escena, int ancho, int alto) throws IOException{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(this.PAQUETE_VIEW + escena));
        AnchorPane root = (AnchorPane) cargadorFXML.load();
        Scene scene = new Scene(root,ancho,alto);
        scene.getStylesheets().add("org/kalum/core/resources/styles/estilo.css");
        this.escenarioPrincipal.setScene(scene);
        this.escenarioPrincipal.sizeToScene();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
