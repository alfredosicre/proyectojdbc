import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;



public class Class04InsertarDepartamento {

    // psvm
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String connecString = "jdbc:mysql://localhost:3306/hospital";
            
            Connection cn = DriverManager.getConnection(connecString, "root", "root");

            // si queremos administrar los commit pondriamos:
            // cn.setAutoCommit(false);
            // porque esta version de BBDD lo tiene activo por defecto 

            // pedimos los datos por pantalla
            System.out.println("Id departamento: ");
            String id = teclado.nextLine();
            System.out.println("Nombre departamento: ");
            String nombre = teclado.nextLine();
            System.out.println("Localizacion departamento: ");
            String loc = teclado.nextLine();

// insert into DEPT values (50, 'TEST', 'LOC TEST');
            String sql = "insert into DEPT values (" + id + ", '" + nombre + "', '" + loc + "')"; // lo copiamos segun el formato de arriba
            Statement st = cn.createStatement();

            int registros = st.executeUpdate(sql);
            System.out.println("Registros insertados: " + registros);

            // al ser una BBDD transaccional debemos hacer que los cambios son permanentes.
            // 1.- actualizar cambios con commit
            // 2.- deshacer cambios con rollback

            // en este caso el commit no es necesario porque esta version de la BBDD el commit esta true por defecto
            // cn.commit();

            // liberamos los recursos y cerramos las conexion
            cn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Class" + e);

        } catch (SQLException ex) {
            System.out.println("Sql" + ex);
        }

    } 
}
