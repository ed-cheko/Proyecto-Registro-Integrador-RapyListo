package pe.edu.upeu.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

import pe.edu.upeu.tablas.Pedidos;
import pe.edu.upeu.tablas.Productos;

public class PedidosDao extends AppCrud{
    Pedidos pedTO;
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    SimpleDateFormat formateadorF = new SimpleDateFormat("dd-MM-yyyy");
    LeerArchivo leerArch;
    LeerTeclado teclado=new LeerTeclado();    
    public Object[][] registrarPedido(){        
        leerArch=new LeerArchivo("Productos.txt");
        
        Object[][] productos=listarContenido(leerArch);
        if(productos!=null){
            for(int i=0;i<productos.length;i++){
                System.out.print(productos[i][0]+"/"+productos[i][1]+"/"+productos[i][2]+".00 Soles \n");
            }
            System.out.println("");
            pedTO=new Pedidos(); 
            pedTO.setPedidoId(generarPedidoId(0));
            leerArch=new LeerArchivo("Productos.txt");
            pedTO.setProductoId(teclado.leer("", "Ingrese el Codigo del Producto:"));
            productos=buscarContenido(leerArch, 0, pedTO.getProductoId());
            pedTO.setDescripcionPed(productos[0][1].toString());
            pedTO.setCantidad(teclado.leer(0.0, "Ingrese la cantidad del producto:"));
            pedTO.setPrecioUnit(teclado.leer(0.0, "Ingrese el precio Producto:"));
            pedTO.setPrecioTotal(pedTO.getCantidad()*pedTO.getPrecioUnit()); 
            pedTO.setFechaRegPed(formateador.format(new Date()));           
        
            leerArch=new LeerArchivo("Pedidos.txt");
        }else{
            crearProducto();
            leerArch.clearConsole();
            registrarPedido();
        }

        return agregarContenido(leerArch, pedTO);
    }

    public Object[][] crearProducto(){       
        leerArch=new LeerArchivo("Productos.txt");
        String continuar="S";
        do{
            Productos prodTO=new Productos();
            prodTO.setProductoId(teclado.leer("", "Ingrese el codigo de Tipo de Productos:"));
            prodTO.setNombreProd(teclado.leer("", "Ingrese del nombre de tipo de Producto:")); 
            agregarContenido(leerArch, prodTO);
            continuar=teclado.leer("S", "Desea Continuar Ingresando Productos? (S=SI, N=NO):");
        }while(continuar.toUpperCase().equals("S"));
        return listarContenido(leerArch);
      }

    public String generarPedidoId(int columna){
        Object[][] data=listarContenido(new LeerArchivo("Pedidos.txt"));
        int serie=1;
        try {
            if(data!=null){
                serie=Integer.parseInt(data[data.length-1][columna].toString().substring(1))+1;
            }     
        } catch (Exception e) {
            System.err.println("Error:"+e.getMessage());
        }
        return "P"+serie;
    }

    public void reportarPedidos(){
        System.out.println("*****************Pedidos Realizados*****************");
        leerArch=new LeerArchivo("Pedidos.txt");
        imprimirLista(listarContenido(leerArch));
    }

    
    public Object[][] reportePedidoFecha(String fecha){
        leerArch=new LeerArchivo("Pedidos.txt");
        Object[][] dataGobal=listarContenido(leerArch);
        Object[][] dataNew=null;
        int cantPed=0;
        double montoRec=0.0;
        int numFilas=0;
        if(dataGobal!=null && !fecha.equals("")){
            for(int fila=0; fila<dataGobal.length;fila++){
                String[] fechaCompleta=dataGobal[fila][6].toString().split(" ");
                if(fechaCompleta[0].equals(fecha)){
                    numFilas++;
                }
            }
            dataNew=new Object[numFilas][dataGobal[0].length];
            int filaX=0, comulnaX=0;
            for(int fila=0;fila<dataGobal.length;fila++){
                String[] fechaCompleta=dataGobal[fila][6].toString().split(" ");
                if(fechaCompleta[0].equals(fecha)){
                    for(int columna=0;columna<dataGobal[0].length;columna++){                    
                        dataNew[filaX][comulnaX]=dataGobal[fila][columna];
                        if(columna==5){
                            montoRec+=Double.parseDouble(String.valueOf(dataGobal[fila][columna]));
                        }
                        comulnaX++;
                    }
                    cantPed++;
                    filaX++; 
                    comulnaX=0;  
                }
            }
        }else{
            dataNew=dataGobal;
            for(int f=0;f<dataNew.length;f++){
                cantPed++;
                montoRec+=Double.parseDouble(String.valueOf(dataNew[f][5]));
            }
        }
        imprimirLista(dataNew);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Cantidad de Pedidos:" + cantPed+"********Monto recaudado:"+montoRec);
        System.out.println("--------------------------------------------------------------------");
        return dataNew;
    }

    public Object[][] reportePedidoRangoFecha(String fechaInit, String fechaFin){
        leerArch=new LeerArchivo("Pedidos.txt");
        Object[][] dataGobal=listarContenido(leerArch);
        Object[][] dataNew=null;
        int cantPed=0;
        double montoRec=0.0;
        int numFilas=0;
        try {
            
        if(dataGobal!=null && !fechaInit.equals("") && !fechaFin.equals("")){
            for(int fila=0; fila<dataGobal.length;fila++){
                String[] fechaCompleta=dataGobal[fila][6].toString().split(" ");
                Date fechaFormat=formateadorF.parse(fechaCompleta[0].toString());
                if((fechaFormat.after(formateadorF.parse(fechaInit)) && fechaFormat.before(formateadorF.parse(fechaFin))) 
                || fechaCompleta[0].toString().equals(fechaInit) 
                || fechaCompleta[0].toString().equals(fechaFin)){
                    numFilas++;
                }
            }
            dataNew=new Object[numFilas][dataGobal[0].length];
            int filaX=0, comulnaX=0;
            for(int fila=0;fila<dataGobal.length;fila++){
                String[] fechaCompleta=dataGobal[fila][6].toString().split(" ");
                Date fechaFormat=formateadorF.parse(fechaCompleta[0].toString());

                if((fechaFormat.after(formateadorF.parse(fechaInit)) && fechaFormat.before(formateadorF.parse(fechaFin))) 
                || fechaCompleta[0].toString().equals(fechaInit) 
                || fechaCompleta[0].toString().equals(fechaFin)){
                    for(int columna=0;columna<dataGobal[0].length;columna++){                    
                        dataNew[filaX][comulnaX]=dataGobal[fila][columna];
                        if(columna==5){
                            montoRec+=Double.parseDouble(String.valueOf(dataGobal[fila][columna]));
                        }
                        comulnaX++;
                    }
                    cantPed++;
                    filaX++; 
                    comulnaX=0;  
                }
            }
        }else{
            dataNew=dataGobal;
            for(int f=0;f<dataNew.length;f++){
                cantPed++;
                montoRec+=Double.parseDouble(String.valueOf(dataNew[f][5]));
            }
        }
        } catch (Exception e) {
            //TODO: handle exception
        }
        imprimirLista(dataNew);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Cantidad de Pedidos:" + cantPed+"********Monto recaudado:"+montoRec);
        System.out.println("--------------------------------------------------------------------");
        return dataNew;
    }


    public Object[][] editarRegistroPedido(){
        leerArch=new LeerArchivo("Pedidos.txt");
        String id=teclado.leer("", "Ingrese el Id del Pedido:");        
        Pedidos ped=new Pedidos();
        ped.setDescripcionPed(teclado.leer("", "Ingrese la nueva descripcion:"));
        ped.setProductoId(teclado.leer("", "Ingrese Id Producto:"));       
        Object[][] data=editarRegistro(leerArch, 0, id, ped);
        return data;
    }


    public Object[][] eliminarPedido(){
        leerArch=new LeerArchivo("Pedidos.txt");
        String id=teclado.leer("", "Ingrese el Id del Pedido:");  
        Object[][] data=eliminarRegistros(leerArch, 0, id);
        return data;
    }


}