package org.kalum.core.models;

public class Student extends Person{
    private String studentId;

    public Student() {
    }

    public Student(String studentId,String nombres, String apellidos, int edad, String telefono, String email, String direccion) {
        super(nombres,apellidos,edad,telefono,email,direccion);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
