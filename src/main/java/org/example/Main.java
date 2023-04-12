package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ConectorDbMySql conector = new ConectorDbMySql();
        conector.agregarEmpleado(41236548, "Federico","Aguirre", "argentina",2);
        conector.agregarEmpleado(26598784, "Agustina","Chavez", "argentina",1);

        conector.modificarNacionalidad(26598784, "paraguaya");

        conector.borrarDepartamento(3);

        ArrayList<Empleado> empleados = conector.consultarEmpleadosPorDepartamento("Logistica");
        for(Empleado e : empleados)
            System.out.println("- " +e.getNombre() + " " +e.getApellido()+":\n" +
                    e.getDni() + " - " + e.getNacionalidad()+" - " +e.getDepartamento());

        ArrayList<Departamento> departamentos =conector.consultarDepartamentosPorOrden();
        for(Departamento d : departamentos)
            System.out.println(d.getId() + ": " +d.getNombre());
    }
}