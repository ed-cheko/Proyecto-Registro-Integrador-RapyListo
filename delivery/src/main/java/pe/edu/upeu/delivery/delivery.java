package pe.edu.upeu.delivery;

//import librerias nativas
import java.util.Scanner;
import java.io.IOException;
//import  locales creadas tablas o clases con set y get
import pe.edu.upeu.tablas.Clientes;
import pe.edu.upeu.tablas.Pedidos;
import pe.edu.upeu.tablas.Productos;
import pe.edu.upeu.tablas.Proveedores;
import pe.edu.upeu.tablas.Territorios;
import pe.edu.upeu.utils.LeerArchivo;
import pe.edu.upeu.utils.LeerTeclado;
import pe.edu.upeu.utils.AppCrud;
import pe.edu.upeu.utils.PedidosDao;




public class delivery {
  static LeerTeclado teclado=new LeerTeclado();
  static AppCrud crudObj;
  static LeerArchivo archObj;

  static Scanner leer=new Scanner(System.in);
  
 
          
  public final static void clearConsole(){
    try{            
        final String os = System.getProperty("os.name");    
        if (os.contains("Windows")){
           new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }else{
            new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
        }
    }
    catch (final IOException | InterruptedException e){
       System.out.println("Error: "+e.getMessage());
    }
}

  public static Object[][] crearCliente(){    
    crudObj=new AppCrud();
    archObj=new LeerArchivo("Clientes.txt");
    Clientes clienteTO=new Clientes();
    clienteTO.setDniId(teclado.leer("", "Ingrese el dni:"));
    clienteTO.setNombreApellidos(teclado.leer("","Ingrese el nombre completo:"));
    clienteTO.setNumTelf(teclado.leer("","Ingrese el numero de celular:"));
    clienteTO.setDireccion(teclado.leer("","Ingrese la direccion donde vive:"));   
    clearConsole();     
    return crudObj.agregarContenido(archObj, clienteTO);
  }

  public static Object[][] crearProducto(){
    crudObj=new AppCrud();
    archObj=new LeerArchivo("Productos.txt");
    Productos prodTO=new Productos();
    prodTO.setProductoId(teclado.leer("", "Ingrese el codigo de Tipo de Productos:"));
    prodTO.setNombreProd(teclado.leer("", "Ingrese del nombre de tipo de Producto:"));
    prodTO.setPrecio(teclado.leer(0, "Ingrese el precio del Producto:"));
    return crudObj.agregarContenido(archObj, prodTO);
  }

    public static void main( String[] args ){
        teclado=new LeerTeclado();
        System.out.println( "##### INICIO DE SESION RAPYLISTO #####" );        
        String opcion="SI";
        int numAlg;
        String  usuario = "usuario" ;
        String  claves = "123456789";
        String usuing, claing;
        
        //Autenticacion de Usuario
        usuing=teclado.leer("","Ingrese su Usuario: ");
        claing=teclado.leer("","Ingrese su clave: ");
       
        if ((usuario.equals(usuing)) && (claves.equals(claing))) { //Condicional y Relacional
            
          String algoritmosNombres=
                  
          "\n##### MENU RAPYLISTO #####\n"+
          "\n1= Crear Cliente\n"+
          "2= Listar cliente\n"+
          "3= Crear Productos\n"+
          "4= REALIZAR PEDIDO";
          //"5= Crear Proveedores: ";
          //"6= Registrar Ubicacion: ";
 
          PedidosDao pedDao;
    
         
        do{
          System.out.println(algoritmosNombres);          
          numAlg=teclado.leer(0,"Elegir opcion: ");
          switch(numAlg){
 
            
            case 1:
            clearConsole();
            crudObj=new AppCrud();
            crudObj.imprimirLista(crearCliente());
            break;
            case 2:
            clearConsole();
            crudObj=new AppCrud();
            archObj=new LeerArchivo("Clientes.txt");
            crudObj.imprimirLista(crudObj.listarContenido(archObj));
            break;
            case 3:
            clearConsole();
            crudObj=new AppCrud();
            crudObj.imprimirLista(crearProducto());  
            break;
            case 4:
            clearConsole();
            pedDao=new PedidosDao();
            pedDao.registrarPedido();
            clearConsole();
            pedDao.reportarPedidos();
            break;
            case 5:
            pedDao=new PedidosDao();
            clearConsole();
            pedDao.reportePedidoFecha(teclado.leer("", "Ingrese la fecha (dd-MM-yyyy)"));
            break;
            case 6:
            pedDao=new PedidosDao();
            pedDao.reportarPedidos();
            
            System.out.println("******************Modificar Pedido******");                    
            pedDao=new PedidosDao();
            pedDao.editarRegistroPedido();
            pedDao=new PedidosDao();
            pedDao.reportarPedidos();            
            break;
            case 7:
            pedDao=new PedidosDao();
            pedDao.reportarPedidos();
            pedDao=new PedidosDao();
            pedDao.eliminarPedido();
            clearConsole();
            pedDao.reportarPedidos();
            break;
            case 8:
            pedDao=new PedidosDao();
            clearConsole();
            pedDao.reportePedidoRangoFecha(
              teclado.leer("", "Ingrese Fecha Inicio (dd-MM-yyyy)"), 
              teclado.leer("", "Ingrese Fecha Final (dd-MM-yyyy)"));
            break;


            default: System.out.println("Num de Algoritmo no existe!!"); break;
          }          
          opcion=teclado.leer("","Desea probar mas algoritmos? SI/NO");  
        }while(opcion.equals("SI"));
            
        }else {
            
            System.exit(0);
        }
       /* String usuario=txtusuario.getText();
        String password=clave.getText();
        boolean mensaje=false;
        for (int i=0; i < Usuarios.length; i++){
            if(Usuarios[i].equals(usuario)&&Claves[i].equals(password)){
                mensaje=true;
            }
        */
        
          
    }
}