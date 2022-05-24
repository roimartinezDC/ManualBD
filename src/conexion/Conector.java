package conexion;

import gui.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Conector {
    
    private static final String URL = ".\\BDTrabajo.db";
    private static Connection connect;
    
    public static void connect() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:"+URL);
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
        }
    }
    
    public static void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void vaciarTabla(JTable tabla) {
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "dni", "nombre", "cpost", "fechanac", "email", "tlf"
            }
        ));
    }
    public static void llenarTabla(JTable tabla) {
        ResultSet result = null;
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        try {
            PreparedStatement st = connect.prepareStatement("select * from usuarios");
            result = st.executeQuery();
            while (result.next()) {
                modelo.addRow(new Object[]{result.getString("dni"), result.getString("nombre"), result.getInt("cpost"), result.getString("fechanac"), result.getString("email"), result.getInt("tlf")});
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void insertarUsuario(Usuario usuario) {
        try {
            PreparedStatement st = connect.prepareStatement("insert into usuarios values (?,?,?,?,?,?)");
            st.setString(1, usuario.getDni());
            st.setString(2, usuario.getNombre());
            st.setInt(3, usuario.getcPost());
            st.setString(4, usuario.getFechaN());
            st.setString(5, usuario.getCorreo());
            st.setInt(6, usuario.getTlf());
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void eliminarUsuario(String dni) {
        try {
            PreparedStatement st = connect.prepareStatement("delete from usuarios where dni = (?)");
            st.setString(1, dni);
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void modificarUsuario(String dni, String cmod, String nvalor) {
        try {
            PreparedStatement st;
            switch(cmod) {
                case "nombre":
                    st = connect.prepareStatement("update usuarios set nombre = (?) where dni = (?)");
                    st.setString(1, nvalor);
                    st.setString(2, dni);
                    st.execute();
                    break;
                case "cpost":
                    st = connect.prepareStatement("update usuarios set cpost = (?) where dni = (?)");
                    st.setInt(1, Integer.parseInt(nvalor));
                    st.setString(2, dni);
                    st.execute();
                    break;
                case "fechanac":
                    st = connect.prepareStatement("update usuarios set fechanac = (?) where dni = (?)");
                    st.setString(1, nvalor);
                    st.setString(2, dni);
                    st.execute();
                    break;
                case "email":
                    st = connect.prepareStatement("update usuarios set email = (?) where dni = (?)");
                    st.setString(1, nvalor);
                    st.setString(2, dni);
                    st.execute();
                    break;
                case "tlf":
                    st = connect.prepareStatement("update usuarios set tlf = (?) where dni = (?)");
                    st.setInt(1, Integer.parseInt(nvalor));
                    st.setString(2, dni);
                    st.execute();
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

