package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable {

    private ServerSocket server;
    private Socket sc;
    private final int puerto;
    private final PropertyChangeSupport support;

    // Constructor
    public Servidor(int puerto) {
        this.puerto = puerto;
        this.support = new PropertyChangeSupport(this);  // Inicialización del soporte para cambios
    }

    // Agregar un listener para las notificaciones
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void run() {
        System.out.println("Se inició el servidor");
        try {
            server = new ServerSocket(puerto);

            while (true) {
                sc = server.accept();
                System.out.println("NUEVO CLIENTE CONECTADO");
                Asistente asistente = new Asistente(sc, this);
                Thread t = new Thread(asistente);
                t.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Notificar a los observadores cuando haya un mensaje
     * @param mensaje Mensaje a enviar
     */
    public void notificacion(String mensaje) {
        support.firePropertyChange("mensaje", null, mensaje + "\n");  // Notificar el cambio
        System.out.println(mensaje);
    }
}
