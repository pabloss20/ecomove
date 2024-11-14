package modelo;


import java.io.*;

public class Registro {

    private static final String ARCHIVO_USUARIOS = "src/resources/files/usuarios.txt";  // Ruta del archivo

    public Registro(){}

    /***
     * Método que se encarga de registrar a los usuarios en el archivo de texto
     * @param nombre
     * @param apellido
     * @param correo
     * @param contrasena
     * @param tipoUsuario
     */
    public static void registrarUsuario(String nombre, String apellido, String correo, String contrasena, String tipoUsuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
            // Aquí agregamos apellido para tener 5 campos en total
            writer.write(nombre + "," + apellido + "," + correo + "," + contrasena + "," + tipoUsuario);
            writer.newLine();  // Nueva línea después de cada usuario
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    public static Usuario iniciarSesion(String correo, String contrasena) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                // Ahora esperamos 5 partes: nombre, apellido, correo, contrasena y tipoUsuario
                if (partes.length == 5 && partes[2].equals(correo) && partes[3].equals(contrasena)) {
                    // Dependiendo del tipoUsuario, devolvemos el objeto adecuado
                    if (partes[4].equals("admin")) {
                        return new Administrador(partes[0], partes[1], partes[2], partes[3]);
                    } else {
                        return new Cliente(partes[0], partes[1], partes[2], partes[3]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }

        return null;  // Si no se encuentra el usuario o la contraseña
    }
}
