package org.kalum.core.models;

public class Teacher extends Person {
    private int employeeId;

    public Teacher(){

    }

    public Teacher(int employeeId,String nombres, String apellidos, int edad, String telefono, String email, String direccion){
        super(nombres,apellidos,edad,telefono,email,direccion);
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
