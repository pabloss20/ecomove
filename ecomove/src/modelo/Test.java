package modelo;

public class Test 
{
    public static void main(String[] args) {
        // Registro de usuarios
        Registro.registrarUsuario("Juan", "Pérez", "juan.perez@example.com", "clave123", "administrador");
        Registro.registrarUsuario("Maria", "Gómez", "maria.gomez@example.com", "clave456", "cliente");
        
        // Intento de iniciar sesión
        Usuario usuario = Registro.iniciarSesion("juan.perez@example.com", "clave123");
        if (usuario != null) 
        {
            if (usuario instanceof Cliente) 
            {
                System.out.println("Inicio de sesión exitoso.");
                System.out.println(usuario.getNombre());
                System.out.println("Cliente");
            }
            else if (usuario instanceof Administrador)
            {
                System.out.println("Inicio de sesión exitoso.");
                System.out.println(usuario.getNombre());
                System.out.println("Administrador");
            }
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

}
