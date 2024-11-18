package modelo;

public class Cliente extends Usuario
{

    private final int puntos;

    public Cliente(String nombre, String apellido, String correo, String contrasena)
    {
        super(nombre, apellido, correo, contrasena);
        this.puntos = 0;

        iniciar();
    }

    public int getPuntos(){return puntos;}

}
