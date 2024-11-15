package modelo;


import java.io.*;

public class Registro {

    private static final String ARCHIVO_USUARIOS = "src/resources/files/usuarios.txt";  

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
            writer.write(nombre + "," + apellido + "," + correo + "," + contrasena + "," + tipoUsuario);
            writer.newLine(); 
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    public static Usuario iniciarSesion(String correo, String contrasena) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5 && partes[2].equals(correo) && partes[3].equals(contrasena)) {
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

        return null; 
    }
}
