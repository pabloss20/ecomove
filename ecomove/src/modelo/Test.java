package modelo;

public class Test 
{
    public static void main(String[] args) {
        // Registro de usuarios
        Registro.registrarUsuario("Juan", "Pérez", "juan.perez@example.com", "clave123", "administrador");
        Registro.registrarUsuario("Maria", "Gómez", "maria.gomez@example.com", "clave456", "cliente");
        
        // Intento de iniciar sesión
        Usuario usuario = Registro.iniciarSesion("juan.perez@example.com", "clave123");
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso.");
            System.out.println(usuario.getNombre());
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

}
