import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Class05EliminarEnfermo {

    // psvm
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String connecString = "jdbc:mysql://localhost:3306/hospital";      
            
            Connection cn = DriverManager.getConnection(connecString, "root", "root");

            // primero vemos los enfermos que hay
            String sqlSelect = "select * from ENFERMO";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlSelect);
            
             while (rs.next()) {
                String ins = rs.getString("INSCRIPCION");
                String ape = rs.getString("APELLIDO");
                System.out.println(ins + " - " + ape);
            }


            System.out.println("Inscripcion de Enfermo: ");
            String inscripcion = teclado.nextLine();

// delete from ENFERMO where INSCRIPCION=1234

            // borramos el registro del enfermo seleccionado
            String sql = "delete from ENFERMO where INSCRIPCION=" + inscripcion; // lo copiamos segun el formato de arriba
            Statement stt = cn.createStatement();

            int deleted = stt.executeUpdate(sql);
            System.out.println("Enfermos eliminados: " + deleted); // nos da el numero de registros eleminados

            sqlSelect = "select * from ENFERMO";
            //ResultSet rs = st.executeQuery(sqlSelect);

            // sacamos los enfermos
            while (rs.next()) {
                String ins = rs.getString("INSCRIPCION");
                String ape = rs.getString("APELLIDO");
                System.out.println(ins + " - " + ape);
            }
            rs.close();
            // cerramos la conexion
            cn.close();
        }catch(Exception e){
            System.out.println("Excepcion: " + e);
        }


    }
    
}
