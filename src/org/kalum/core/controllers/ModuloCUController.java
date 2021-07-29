package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.models.Modulo;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class ModuloCUController implements Initializable {
    private Modulo modulo;
    private Main escenarioPrincipal;

    private SpinnerValueFactory<Integer> valueFactorySeminario = new SpinnerValueFactory.IntegerSpinnerValueFactory
            (1,10,0);

    private ObservableList<CarreraTecnica> carreraTecnicas;

    @FXML private TextField txtNombre;
    @FXML private Spinner<Integer> numSeminarios;
    @FXML private ComboBox<CarreraTecnica> cmbCarrera;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numSeminarios.setValueFactory(this.valueFactorySeminario);
        try{
            this.carreraTecnicas = FXCollections.observableArrayList((List<CarreraTecnica>) ConexionPU
                                    .getInstacia()
                                    .findAll("CarreraTecnica.findAll"));
            this.cmbCarrera.setItems(this.carreraTecnicas);
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de modulos",e.getMessage(),50301);
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void regresar(){
        this.escenarioPrincipal.mostrarEscenaModulo();
    }

    public void setModulo(Modulo modulo){
        this.modulo = modulo;
        this.cmbCarrera.getSelectionModel().select(modulo.getCarreraTecnica());
        this.txtNombre.setText(modulo.getNombreModulo());
        this.valueFactorySeminario.setValue(modulo.getNumeroSeminarios());
    }

    public void guardar(){
        try{
            if(this.modulo == null){
                Modulo nuevoModulo = new Modulo();
                nuevoModulo.setModuloId(UUID.randomUUID().toString());
                nuevoModulo.setNumeroSeminarios(this.numSeminarios.getValue());
                nuevoModulo.setNombreModulo(this.txtNombre.getText());
                nuevoModulo.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                ConexionPU.getInstacia().agregar(nuevoModulo);
            }else if(this.modulo != null){
                this.modulo.setNombreModulo(txtNombre.getText());
                this.modulo.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                this.modulo.setNumeroSeminarios(numSeminarios.getValue());
                ConexionPU.getInstacia().modificar(this.modulo);
            }
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de modulos");
            aviso.setContentText("Regristro almacenado correctamente!");
            aviso.show();
        }catch (KalumException e){
            KalumViewMessage.getInstancia().mostrarMensaje("Adminsitración de modulos",e.getMessage(),50301);
        }
    }
}
