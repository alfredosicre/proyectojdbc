import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.Scanner;

public class Class02BuscadorEmpleadosDept {

    // psvm
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca numero de departamento: ");
        String idDepartamento = teclado.nextLine();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String connecString = "jdbc:mysql://localhost:3306/hospital"; 
            Connection cn = DriverManager.getConnection(connecString, "root", "root");

            String sql = "select APELLIDO, OFICIO from EMP where DEPT_NO=" + idDepartamento;
            Statement st = cn.createStatement(); // consulta simple

            ResultSet rs = st.executeQuery(sql);
            rs.next();

            while (rs.next()) {
                
                String apellido = rs.getString("APELLIDO");
                String oficio = rs.getString("OFICIO");

                System.out.println("Apellido: " + apellido + "Departamento: " + oficio );
            }
        // liberamos los recursos
        rs.close();
        cn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Class" + e);

        } catch (SQLException ex) {
            System.out.println("Sql" + ex);
        }
        
    }  
}
