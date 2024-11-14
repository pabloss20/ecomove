package modelo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Asistente implements Runnable {

    private final Socket sc;
    private DataInputStream in;
    private final Servidor server;
    private boolean centinela;

    // Constructor
    public Asistente(Socket sc, Servidor server) {
        this.sc = sc;
        this.server = server;
        this.centinela = true;
    }

    @Override
    public void run() {
        try {
            String mensaje;
            in = new DataInputStream(sc.getInputStream());

            while (centinela) {
                // Leer el mensaje del cliente
                mensaje = in.readUTF();

                if (mensaje.equalsIgnoreCase("cerrar")) {
                    centinela = false;
                    System.out.println("UN CLIENTE SE DESCONECTÓ");
                    in.close();
                    sc.close();
                } else {
                    // Enviar el mensaje al servidor
                    server.notificacion(mensaje);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Asistente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
