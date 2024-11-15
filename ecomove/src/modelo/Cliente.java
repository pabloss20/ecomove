package modelo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Usuario
{

    private final int puntos;

    private Socket sc; // socket cliente (sc)
    private DataOutputStream out;
    final String HOST = "127.0.0.1";
    private final int puerto = 6000;

    public Cliente(String nombre, String apellido, String correo, String contrasena)
    {
        super(nombre, apellido, correo, contrasena);
        this.puntos = 0;

        iniciar();
    }

    public final void iniciar()
    {
        try {
            sc = new Socket(HOST, puerto);
        } catch (IOException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void enviarMensaje(String msj) {

        if (sc.isClosed()) {
            System.out.println("socket cerrado");
        } else {

            try {

                out = new DataOutputStream(sc.getOutputStream());
                out.writeUTF(msj);
                

            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {
        return sc;
    }

    public int getPuntos(){return puntos;}

}
