package modelo;

import java.io.Serializable;

public abstract class Residuo implements Serializable
{

    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final String ruta;
    private final String informacion;
    private final int capacidad;

    public Residuo (String residuo, String ruta, String informacion, int capacidad)
    {
        this.nombre = residuo;
        this.ruta = ruta;
        this.informacion = informacion;
        this.capacidad = capacidad;
    }

    public String getNombre(){return nombre;}
    public String getRuta(){return ruta;}
    public String getInfo(){return informacion;}
    public int getCapacidad(){return capacidad;}

    @Override
    public String toString() {
        return String.format("%s (%s)", nombre, this.getClass().getSimpleName());
    }
}