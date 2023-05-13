package org.swing.cancionesautoresmysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcos Miranda
 */
public class App {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        GestoraCancionesAutores Siri=new GestoraCancionesAutores();
        
        try {

            //1. Retorna todass las sucrusales, (devuelve un ResultSet que contiene todos los datos de las sucursales),  
            //   también los puede devolver ordenadas por nombre si el usuario lo desea. 
            ResultSet canciones = Siri.retornarCanciones();
            System.out.println("*****MOSTRANDO CANCIONES*****");
            Siri.mostrarResultSet(canciones);

            //2. Retornamos todos los clientes
            ResultSet autores = Siri.retornarAutores();
             System.out.println("*****MOSTRANDO AUTORES*****");
            Siri.mostrarResultSet(autores);

            /*
            //3.insertamos una cancion
            ArrayList<String> listaCanciones = new ArrayList<>();             //creamos el array para añadirle valores
            listaCanciones.add("6");                                      //añadimos valores
            listaCanciones.add("bizarrap session 50");
            listaCanciones.add("2.85");
                        
            if(Siri.insertarCancion(listaCanciones)){
                System.out.println("Cancion añadida con éxito");
            } else {
                System.out.println("no se ha podido añadir la cancion");
            }
            */ 
            
            //4.incrementar duracion de canciones
                if(Siri.incrementarDuracion(9.99f)){
                    System.out.println("duracion incrementada correctamente");
                } else{
                    System.out.println("no se ha podido incrementar la duracion");
                } 
                    
            
 
                       
            

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
        
    }

