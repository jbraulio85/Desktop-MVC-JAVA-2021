package org.kalum.core.utils;

public class KalumException extends Exception{
    private String message;
    private int codigoError;

    public KalumException(){

    }

    public KalumException(int codigoError){
        this.codigoError = codigoError;
    }

    @Override
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        if(codigoError == 50301){
            this.message = "Error en el legado de la base de datos";
        }else if(codigoError == 50302) {
            this.message = "Error al momento de obtener la información de la base de datos";
        }else if(codigoError == 503003){
            this.message = "Error al momento de guardar la información en la base de datos";
        }
        this.message = message;
    }
}
