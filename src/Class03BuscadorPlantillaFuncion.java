import java.sql.Statement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Class03BuscadorPlantillaFuncion {

    // psvm
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca la funcion: ");
        String idFuncion = teclado.nextLine();
        String idFuncionM = idFuncion.toUpperCase();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String connecString = "jdbc:mysql://localhost:3306/hospital"; 
            Connection cn = DriverManager.getConnection(connecString, "root", "root");
        
            String sql = "select APELLIDO, FUNCION, SALARIO from PLANTILLA where FUNCION='" + idFuncionM + "'";
            
            Statement st = cn.createStatement(); // consulta simple

            ResultSet rs = st.executeQuery(sql);
            rs.next();

            while (rs.next()) {
                
                String apellido = rs.getString("APELLIDO");
                String funcion = rs.getString("FUNCION");
                int salario = rs.getInt("SALARIO");

                System.out.println("Apellido: " + apellido + " Funcion: " + funcion + " Salario: " + salario);
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