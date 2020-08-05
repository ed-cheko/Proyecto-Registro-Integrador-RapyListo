/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.tablas;

import java.util.*;
/**
 *
 * @author iCE
 */
public class Pedidos {
    public String pedidoId;
    public String productoId;
    public String descripcionPed;
    public double cantidad;
    public double precioUnit;
    public double precioTotal;
   public String fechaRegPed;

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getDescripcionPed() {
        return descripcionPed;
    }

    public void setDescripcionPed(String descripcionPed) {
        this.descripcionPed = descripcionPed;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

   public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFechaRegPed() {
        return fechaRegPed;
    }

    public void setFechaRegPed(String fechaRegPed) {
        this.fechaRegPed = fechaRegPed;
    }

    
}