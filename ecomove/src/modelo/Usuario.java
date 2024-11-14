package modelo;

public class Usuario 
{

    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String contrasena;

    public Usuario(String nombre, String apellido, String correo, String contrasena)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getCorreo(){return correo;}
    public String getContrasena(){return contrasena;}
}
