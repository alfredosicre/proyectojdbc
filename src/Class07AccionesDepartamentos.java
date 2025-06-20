import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Class07AccionesDepartamentos {

    // psvm
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connecString = "jdbc:mysql://localhost:3306/hospital";      
            Connection cn = DriverManager.getConnection(connecString, "root", "root");  // conectados
            //-----------------------------------------------------------------------------------------------------
            // primero miramos que hay en la tabla departamentos DEPT
            // select * from DEPT;
            String sql = "select * from DEPT"; // lo copiamos segun el formato de arriba
            Statement st = cn.createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String codDep = rs.getString("DEPT_NO");
                String nombreDep = rs.getString("DNOMBRE");
                String localidad = rs.getString("LOC");
                System.out.println(codDep + " - " + nombreDep + " - " + localidad);
            }   
            
            // mostramos el menu para seleccioner una opcion:
            System.out.println("");
            System.out.println("Introduzca opcion: ");
            System.out.println("");
            System.out.println("1.- Insertar departamento.");
            System.out.println("2.- Modificar departamento.");
            System.out.println("3.- Eliminar departamento.");
            System.out.println("");
            String op = teclado.nextLine();
            int numero = Integer.parseInt(op);


            if (numero == 1) {
                // insertar departamento
                // pedimos los datos por pantalla
                System.out.println("Id departamento: ");
                String id = teclado.nextLine();
                System.out.println("Nombre departamento: ");
                String nombre = teclado.nextLine();
                System.out.println("Localizacion departamento: ");
                String loc = teclado.nextLine();

                // insert into DEPT values (50, 'TEST', 'LOC TEST')
                sql = "insert into DEPT values (" + id + ", '" + nombre + "', '" + loc + "')"; // lo copiamos segun el formato de arriba
                Statement st1 = cn.createStatement();

                int registros = st1.executeUpdate(sql);
                System.out.println("Registros insertados: " + registros);

            }else if (numero == 2) {
                // modificar departamento
                // pedimos los datos
                System.out.println("Id departamento: ");
                String id = teclado.nextLine();
                System.out.println("Nombre departamento: ");
                String nombre = teclado.nextLine();
                System.out.println("Localizacion departamento: ");
                String loc = teclado.nextLine();

                //update DEPT set DNOMBRE='aaa', LOC='aaa' where DEP_NO=12
                sql = "update DEPT set DNOMBRE='" + nombre + "', LOC='" + loc + "' where DEPT_NO=" + id; // lo copiamos segun el formato de arriba
                Statement st1 = cn.createStatement();

                int registros = st1.executeUpdate(sql);
                System.out.println("Registros actualizados: " + registros);

            }else if (numero == 3) {
                // eliminar departamento
                // pedimos el id de departamento
                System.out.println("Id departamento: ");
                String id = teclado.nextLine();
                
                // delete from DEPT where DEPT_NO=(60);
                sql = "delete from DEPT where DEPT_NO=(" + id + ")"; // lo copiamos segun el formato de arriba
                Statement st1 = cn.createStatement();

                int registros = st1.executeUpdate(sql);
                System.out.println("Registros eleminados: " + registros);
            }



            rs.close();
            // cerramos la conexion
            cn.close();
        } catch (Exception e) {
            System.out.println("Excepcion: " + e);
        }


    }
    
}
