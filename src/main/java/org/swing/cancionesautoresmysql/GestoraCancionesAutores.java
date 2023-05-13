package org.swing.cancionesautoresmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marcos Miranda
 */
public class GestoraCancionesAutores {

    /**
     * metodo que establece la conexión a la base de datos creada en workbench
     * CancionesAutores
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection conexionBD() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String cadenaConexion = "jdbc:mysql://localhost:3306/CancionesAutores?user=root&password=pruebas";
        Connection conexionBD = DriverManager.getConnection(cadenaConexion);

        return conexionBD;

    }

    /**
     * metodo que agrupa los resultados para mostrar
     *
     * @param resultSet
     * @throws SQLException
     */
    public void mostrarResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();                   // cogemos los metaDatos
        int numeroColumnasMostrar = metaData.getColumnCount();                  //numero de columnas de esos metadatos

        while (resultSet.next()) {
            for (int i = 1; i <= numeroColumnasMostrar; i++) {
                String valorColumna = resultSet.getString(i);
                System.out.println(valorColumna + " | ");
            }
            System.out.println("");

        }
    }

    /**
     * metodo que retorna canciones
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ResultSet retornarCanciones() throws ClassNotFoundException, SQLException {
        Connection conexionBd = this.conexionBD();                    //instanciamos la conexion a la bbdd
        Statement statement = conexionBd.createStatement();           //instancia retorna sentencia normal
        ResultSet resultado;
        resultado = statement.executeQuery("SELECT * FROM CancionesAutores.Cancion ");
        //statement.close();
        return resultado;

    }
    
    /**
     * metodo que retorna los autores
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ResultSet retornarAutores() throws ClassNotFoundException, SQLException {
        Connection conexionBd = this.conexionBD();                    //instanciamos la conexion a la bbdd
        Statement statement1 = conexionBd.createStatement();           //instancia retorna sentencia normal
        ResultSet resultado1;
        resultado1 = statement1.executeQuery("SELECT * FROM CancionesAutores.Autor ");
        //statement1.close();
        return resultado1;

    }
    
    /**
     * metodo que inserta una canción
     * @param valoresCancion
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean insertarCancion(ArrayList<String> valoresCancion) throws ClassNotFoundException, SQLException {
        boolean insertado = false;
        Connection conexionBd = this.conexionBD();                    //instanciamos la conexion a la bbdd
        Statement statement = conexionBd.createStatement();           //instancia retorna sentencia normal

        statement.executeUpdate(
                "INSERT into Cancion (idCancion, titulo, duracion)"
                + " VALUES (" + Integer.valueOf(valoresCancion.get(0)) + ", '" + valoresCancion.get(1) + "', "
                + "'" + Float.valueOf(valoresCancion.get(2)) + "')");
        statement.close();
        insertado = true;

        return insertado;
    }
    
    /**
     * metodo que incrementa la duracion de las canciones 
     * @param listaCanciones
     * @param duracionExtra
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean incrementarDuracion(float duracionExtra) throws ClassNotFoundException, SQLException{
       Connection conexionBd = this.conexionBD();                    //instanciamos la conexion a la bbdd
        Statement statement = conexionBd.createStatement();           //instancia retorna sentencia normal
        boolean incrementado = false; 
        if(duracionExtra>0){
            statement.executeUpdate("UPDATE Cancion SET duracion =" + duracionExtra + "");
            incrementado = true;
            statement.close();
        } else {
            incrementado = false;
            statement.close();
        }
              
        return incrementado;
    }
    
    
    
    /**
     * metodo que elimina canciones por nacionalidad del autor
     * @param nacionalidad
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean eliminarCancionesAutorNacionalidad(String nacionalidad) throws ClassNotFoundException, SQLException {
        Connection conexionBd = this.conexionBD();                    //instanciamos la conexion a la bbdd
        Statement statement = conexionBd.createStatement();           //instancia retorna sentencia normal
        boolean eliminado = false;
        if (nacionalidad.equalsIgnoreCase(nacionalidad)) {
            statement.executeUpdate("DELETE from Cancion WHERE nacionalidad='" + nacionalidad + "'");
            eliminado = true;
            statement.close();
        } else {
            eliminado = false;
            statement.close();
        }
        return eliminado;
    }
    
    

}
