package org.example;
import java.sql.*;
import java.util.ArrayList;


public class ConectorDbMySql {
    private void cargarClase() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    } //Este método se utiliza para registrar la clase que se utilizará como driver.

    public void agregarEmpleado(int dni1, String nombre1, String apellido1, String nacionalidad1, int departamento){
        Statement stmt=null;

        try {
            cargarClase();
            Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net/jorgedb1",
                    "jorgearbach","pass2023");

            stmt = conn.createStatement();

            int filasAfectadas = stmt.executeUpdate("INSERT into empleados (dni,nombre,apellido,nacionalidad,departamento_id)\n" +
                    "VALUES ("+dni1+",'"+nombre1+"','"+apellido1+"','"+nacionalidad1+"',"+departamento+")");
            System.out.println("Filas afectadas " + filasAfectadas);
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        finally {
            if (stmt!=null){
                try {
                    stmt.close();
                }
                catch (Exception e){}
            }
        }
    }

    public void modificarNacionalidad(int dni, String nacionalidad){
        Statement stmt=null;

        try {
            cargarClase();
            Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net/jorgedb1",
                    "jorgearbach","pass2023");

            stmt = conn.createStatement();

            int filasAfectadas = stmt.executeUpdate("UPDATE empleados \n" +
                                                    "SET nacionalidad = '"+nacionalidad+"'\n" +
                                                    "WHERE dni = "+dni);
            System.out.println("Filas afectadas " + filasAfectadas);
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        finally {
            if (stmt!=null){
                try {
                    stmt.close();
                }
                catch (Exception e){}
            }
        }
    }

    public void borrarDepartamento(int departamento_id){
        Statement stmt=null;

        try {
            cargarClase();
            Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net/jorgedb1",
                    "jorgearbach","pass2023");

            stmt = conn.createStatement();

            int filasAfectadas = stmt.executeUpdate("DELETE FROM departamentos \n" +
                    "WHERE departamento_id = "+departamento_id);
            System.out.println("Filas afectadas " + filasAfectadas);
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        finally {
            if (stmt!=null){
                try {
                    stmt.close();
                }
                catch (Exception e){}
            }
        }
    }

    public ArrayList<Departamento> consultarDepartamentosPorOrden(){
        Statement stmt=null;
        ResultSet rs = null;
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

        try {
            cargarClase();
            Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net/jorgedb1",
                    "jorgearbach","pass2023"); //Este método se utiliza para establecer conexión con la DB.

            stmt = conn.createStatement(); //Este método es usado para crear la sentencia. Esta sentencia es la responsable de ejecutar las consultas a la DB.
            rs = stmt.executeQuery("select * from departamentos\n" +
                    "ORDER BY nombre");//Este método realiza la consulta y devuelve el resultado de la misma

            /*while (rs.next())  { //imprime los datos que obtiene de la DB
                for (int x=1;x<=rs.getMetaData().getColumnCount();x++)
                    System.out.println(rs.getString(x)+"\t");
                System.out.println();
            }*/

            while (rs.next())  {
                Departamento depto = new Departamento();
                depto.setNombre(rs.getString("nombre"));
                depto.setId(rs.getInt("departamento_id"));
                departamentos.add(depto);
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        finally {
            if (rs!=null){
                try {
                    rs.close();
                }
                catch (Exception e){}
            }
            if (stmt!=null){
                try {
                    stmt.close();
                }
                catch (Exception e){}
            }
            return departamentos;
        }
    }

    public ArrayList<Empleado> consultarEmpleadosPorDepartamento(String departamento){
        Statement stmt=null;
        ResultSet rs = null;
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();

        try {
            cargarClase();
            Connection conn = DriverManager.getConnection("jdbc:mysql://db4free.net/jorgedb1",
                    "jorgearbach","pass2023");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT *\n" +
                    "FROM empleados, departamentos\n" +
                    "WHERE empleados.departamento_id = departamentos.departamento_id\n" +
                    "AND departamentos.nombre = '"+departamento+"'");

            while (rs.next())  {
                Empleado e = new Empleado();
                e.setDni(rs.getInt("dni"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setNacionalidad(rs.getString("nacionalidad"));
                e.setDepartamento(rs.getInt("departamento_id"));
                empleados.add(e);
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        finally {
            if (rs!=null){
                try {
                    rs.close();
                }
                catch (Exception e){}
            }
            if (stmt!=null){
                try {
                    stmt.close();
                }
                catch (Exception e){}
            }
            return empleados;
        }
    }

}