package modelo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador extends Usuario
{

    
    private Socket sa; // socket administrador (sa)
    private DataOutputStream out;
    final String HOST = "127.0.0.1";
    private final int puerto = 6000;

    public Administrador(String nombre, String apellido, String correo, String contrasena)
    {
        super(nombre, apellido, correo, contrasena);

        iniciar();
    }

        public final void iniciar()
    {
        try {
            sa = new Socket(HOST, puerto);
        } catch (IOException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void enviarMensaje(String msj) {

        if (sa.isClosed()) {
            System.out.println("socket cerrado");
        } else {

            try {

                out = new DataOutputStream(sa.getOutputStream());
                out.writeUTF(msj);
                

            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {
        return sa;
    }
}
