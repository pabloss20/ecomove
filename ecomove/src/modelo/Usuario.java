package modelo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario 
{

    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String contrasena;
    
    private Socket usuarioSocket; 
    private DataOutputStream out;
    final String HOST = "127.0.0.1";
    private final int puerto = 6000;

    public Usuario(String nombre, String apellido, String correo, String contrasena)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    /***
     * Método que se encarga de conectar al usuario en el servidor mediante sockets
     */
    public final void iniciar()
    {
        try {
            usuarioSocket = new Socket(HOST, puerto);
        } catch (IOException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /***
     * Método que se encarga de enviar un mensaje al servidor
     * @param msj contenido del mensaje
     */
    public void enviarMensaje(String msj) {

        if (usuarioSocket.isClosed()) {System.out.println("socket cerrado");}
        else 
        {
            try 
            {
                out = new DataOutputStream(usuarioSocket.getOutputStream());
                out.writeUTF(msj);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {return usuarioSocket;}


    public String getNombre(){return nombre;}
    public String getApellido(){return apellido;}
    public String getCorreo(){return correo;}
    public String getContrasena(){return contrasena;}
}
