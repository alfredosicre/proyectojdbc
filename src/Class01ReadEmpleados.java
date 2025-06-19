import java.sql.*;
import java.sql.DriverManager;

public class Class01ReadEmpleados {

    // psvm
    public static void main(String[] args) {
        
        //1- registrar la clase de nuestro driver de mysql
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // se busca en google y de baja

            // 2- necesitamos una cadena de conexion
            String connectionString = "jdbc:mysql://localhost:3306/hospital"; // aqui se indica tambien usuario y contraseña

            // 3- crear una conexion mediante DRIVERMANAGER
            Connection cn = DriverManager.getConnection(connectionString, "root", "root");

            // 4- consultar sobre la base de datos
            String sql = "select * from EMP";

            // 5- creamos el tipo de statement depemdiendo de la consulta, si tiene parametros o no
            Statement st = cn.createStatement();

            // 6- como es consulta select necesitamos un ResulSet y el metodo executeQuey
            ResultSet rs = st.executeQuery(sql);
            rs.next(); // leemos el primer registro

            // recuperamos el primer apellido
            // rs.next(); // leemos el primer registro, next es un boolean. podemos hacer un bucle while
            while (rs.next()) {
                
                String apellido = rs.getString("APELLIDO");
                System.out.println("Apellido: " + apellido);
            }
        // liberamos los recursos
        rs.close();
        cn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Class" + e);

        } catch (SQLException ex){
            System.out.println("Sql" + ex);
        }

    }
    
}
