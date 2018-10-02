/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

/**
 *
 * @author Alejandro
 */
public class Errores {
    String mensaje,operacion;
    Double oper1,oper2;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Double getOper1() {
        return oper1;
    }

    public void setOper1(Double oper1) {
        this.oper1 = oper1;
    }

    public Double getOper2() {
        return oper2;
    }

    public void setOper2(Double oper2) {
        this.oper2 = oper2;
    }
    
}
