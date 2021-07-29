package org.kalum.core.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Horario;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class HorarioCUController implements Initializable {
    @FXML private JFXComboBox cmbHorarioInicio;
    @FXML private JFXComboBox cmbHorarioFinal;


    private Horario horario;
    private Main escenarioPrincipal;
    private ObservableList<Horario> horarios;
    private ObservableList<Horario> horarios1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.cmbHorarioInicio.getItems().addAll("07:00","07:30","08:00","08:30","09:00","09:30","10:00",
                "10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30",
                "17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00");
        this.cmbHorarioFinal.getItems().addAll("09:00","09:30","10:00","10:30","11:00","11:30","12:00",
                "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00",
                "18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00");
    }

    public void setEscenarioPrincipal (Main escenarioPrincipal){
            this.escenarioPrincipal = escenarioPrincipal;
        }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaHorario();
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
        this.cmbHorarioInicio.getSelectionModel().select(horario.getHorarioInicio());
        this.cmbHorarioFinal.getSelectionModel().select(horario.getHorarioFinal());
    }

    public void guardar() {
        DateFormat formato = new SimpleDateFormat("HH:mm:ss");
        if (horario == null){
            String horarioInicio = cmbHorarioInicio.getSelectionModel().getSelectedItem().toString();
            String horarioFinal = cmbHorarioFinal.getSelectionModel().getSelectedItem().toString();
            Horario horario = new Horario();
            try{
                Date horaInicio = formato.parse(horarioInicio.concat(":00"));
                Date horaFinal = formato.parse(horarioFinal.concat(":00"));
                horario.setHorarioInicio(horaInicio);
                horario.setHorarioFinal(horaFinal);
                horario.setHorarioId(UUID.randomUUID().toString());
                ConexionPU.getInstacia().agregar(horario);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Kalum v1.0.0");
                aviso.setHeaderText("Administración de horarios");
                aviso.setContentText("¡Registro almacenado exitosamente!");
                aviso.show();
                this.escenarioPrincipal.mostrarEscenaHorario();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
