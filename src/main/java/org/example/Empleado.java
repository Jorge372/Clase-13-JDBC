package org.example;

import lombok.*;
public class Empleado {
    @Setter @Getter
    private Integer dni;
    @Setter @Getter
    private String nombre;
    @Setter @Getter
    private String apellido;
    @Setter @Getter
    private String nacionalidad;
    @Setter @Getter
    private int departamento;
}
