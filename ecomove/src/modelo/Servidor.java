package modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable {

    private ServerSocket server;
    private final int puerto;
    private final List<Asistente> clientes; 

    public Servidor(int puerto) {
        this.puerto = puerto;
        this.clientes = new ArrayList<>(); 
    }

    @Override
    public void run() {
        System.out.println("Se inició el servidor");
        try {
            server = new ServerSocket(puerto);

            while (true) {
                Socket sc = server.accept();
                System.out.println("NUEVO CLIENTE CONECTADO");

                Asistente asistente = new Asistente(sc, this);
                synchronized (clientes) {
                    clientes.add(asistente); 
                }
                
                Thread t = new Thread(asistente);
                t.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para enviar un mensaje a todos los clientes conectados
     * @param mensaje Mensaje a enviar
     */
    public void enviarATodos(String mensaje, Asistente emisor) {
        synchronized (clientes) {
            for (Asistente asistente : clientes) {
                if (asistente != emisor) { // No enviar al cliente que lo envió
                    asistente.enviarMensaje(mensaje);
                }
            }
        }
    }    
    
    /**
     * Método para eliminar un cliente de la lista cuando se desconecta
     */
    public void eliminarCliente(Asistente asistente) {
        synchronized (clientes) {
            clientes.remove(asistente);
        }
    }
}
