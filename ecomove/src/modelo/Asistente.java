package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Asistente implements Runnable {

    private final Socket sc;
    private DataInputStream in;
    private DataOutputStream out;
    private final Servidor server;
    private boolean centinela;

    public Asistente(Socket sc, Servidor server) {
        this.sc = sc;
        this.server = server;
        this.centinela = true;
        try {
            out = new DataOutputStream(sc.getOutputStream()); 
        } catch (IOException e) {
            Logger.getLogger(Asistente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(sc.getInputStream());

            while (centinela) {

                String mensaje = in.readUTF();

                if (mensaje.equalsIgnoreCase("cerrar")) {
                    centinela = false;
                    System.out.println("UN CLIENTE SE DESCONECTÓ");
                    server.eliminarCliente(this); 
                    in.close();
                    sc.close();
                } else {
                    server.enviarATodos(mensaje, this);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Asistente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarMensaje(String mensaje) {
        try {
            out.writeUTF(mensaje); 
        } catch (IOException ex) {
            Logger.getLogger(Asistente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
