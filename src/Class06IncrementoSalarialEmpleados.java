import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Class06IncrementoSalarialEmpleados {

    // psvm
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String connecString = "jdbc:mysql://localhost:3306/hospital";      
            Connection cn = DriverManager.getConnection(connecString, "root", "root");  // conectados
            // -------------------------------------------------------------------------------------
            // pedimos por pantalla los datos, OFICIO y INCREMENTO SALARIAL;
            System.out.println("Introduzca el Oficio ");
            String oficio = teclado.nextLine();
            oficio = oficio.toUpperCase();
            System.out.println("Introduzca el incremento salarial ");
            String incremento = teclado.nextLine();

// update EMP set SALARIO = SALARIO + 1 where OFICIO='ANALISTA'
            String sql = "update EMP set SALARIO = SALARIO + " + incremento + " where OFICIO='" + oficio + "'"; // lo copiamos segun el formato de arriba
            Statement st = cn.createStatement();
            
            int update = st.executeUpdate(sql);
            System.out.println("Registros actualizados: " + update); // nos da el numero de registros eleminados

            // sacamos la plantilla y vemos si ha actualizado
            // SELECT * FROM EMP WHERE OFICIO='ANALISTA'
            String sqlList = "select * from EMP where OFICIO='" + oficio + "'";
            ResultSet rs = st.executeQuery(sqlList);
            while (rs.next()) {
                String apellido = rs.getString("APELLIDO");
                String oficios = rs.getString("OFICIO");
                String salario = rs.getString("SALARIO");
                System.out.println(apellido + " - " + oficios + " - " + salario);
            }

            rs.close();
            // cerramos la conexion
            cn.close();
        }catch(Exception e){
            System.out.println("Excepcion: " + e);
        }

    }

    
}
