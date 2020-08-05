/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.tablas;

/**
 *
 * @author iCE
 */
public class Clientes {
    public String dniId;
    public String nombreApellidos;
    public String numTelf;
    public String direccion;

    public String getDniId() {
        return dniId;
    }

    public void setDniId(String dniId) {
        this.dniId = dniId;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getNumTelf() {
        return numTelf;
    }

    public void setNumTelf(String numTelf) {
        this.numTelf = numTelf;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}