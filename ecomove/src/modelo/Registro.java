package modelo;

import java.io.*;

public class Registro {

    private static final String ARCHIVO_USUARIOS = "src/resources/files/usuarios.txt";  

    public Registro(){}

    /***
     * Método que se encarga de registrar a los usuarios en el archivo de texto
     * @param nombre nombre del usuario
     * @param apellido apellido del usuario
     * @param correo correo del usuario
     * @param contrasena contraseña del usuario
     * @param tipoUsuario administrador o cliente
     */
    public static void registrarUsuario(String nombre, String apellido, String correo, String contrasena, String tipoUsuario) {
        if (correoExiste(correo)) {
            System.out.println("Error: El correo ya está registrado.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
            writer.write(nombre + "," + apellido + "," + correo + "," + contrasena + "," + tipoUsuario);
            writer.newLine(); 
        } catch (Exception e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    /***
     * Método que se encarga de verificar si un correo ya está registrado en el sistema
     * @param correo El correo que se desea verificar
     * @return true si el correo ya está registrado, false en caso contrario
     */
    public static boolean correoExiste(String correo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length > 2 && partes[2].equals(correo)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al verificar correo: " + e.getMessage());
        }
        return false;
    }

    /***
     * Método que se encarga de validar la información del usuario para ingresar al sistema
     * @param correo correo del usuario
     * @param contrasena contraseña del usuario
     * @return objeto de tipo administrador o cliente
     */
    public static Usuario iniciarSesion(String correo, String contrasena) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5 && partes[2].equals(correo) && partes[3].equals(contrasena)) {
                    if (partes[4].equals("administrador")) {
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
