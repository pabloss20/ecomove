package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GestorSolicitudes 
{
    private static final String ARCHIVO_SOLICITUDES = "src/resources/files/solicitudes.dat";
    private final List<Solicitud> solicitudes = new ArrayList<>();

    /*** 
     * Método que se encarga de guardar una solicitud en el archivo
     */
    public void guardarSolicitud()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_SOLICITUDES)))
        {
            oos.writeObject(solicitudes);
            System.out.println("Guardado");
        }
        catch (IOException e)
        {
            System.out.println("Error al guardar la solicitud: " + e.getMessage());
        }
    }

    /***
     * Método que se encarga de cargar las solicitudes del archivo a la lista
     */
    @SuppressWarnings("unchecked")
    public void cargarSolicitudes() 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_SOLICITUDES))) 
        {
            solicitudes.clear();
            solicitudes.addAll((List<Solicitud>) ois.readObject());
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.out.println("Error al cargar solicitudes: " + e.getMessage());
        }
    }

    /***
     * Método que se encarga de registrar una solicitud en la lista
     * @param solicitud
     */
    public void registrarSolicitud(Solicitud solicitud) 
    {
        solicitudes.add(solicitud);
        guardarSolicitud();
    }

    /***
     * Método que se encarga de obtener las solicitudes pendientes
     * @return retorna un arraylist de solicitudes pendientes
     */
    public List<Solicitud> getSolicitudesPendientes() 
    {
        List<Solicitud> pendientes = new ArrayList<>();
        for (Solicitud s : solicitudes) 
        {if (s.getEstado() == EstadoSolicitud.PENDIENTE) {pendientes.add(s);}}
        return pendientes;
    }

    /***
     * Método que se encarga de procesar las solicitudes
     * @param solicitud objeto de tipo Solicitud
     * @param aceptar flag booleando que indica la aceptación de la solicitud
     * @param gestorResiduos objeto de tipo GestorResiduos para procesar residuos
     */
    public String procesarSolicitud(Solicitud solicitud, boolean aceptar, GestorResiduos gestorResiduos) 
    {
        String mensaje;

        if (solicitud.getEstado() != EstadoSolicitud.PENDIENTE) 
        {
            mensaje = "Error: La solicitud ya ha sido procesada.";
        }
        else if (aceptar) 
        {
            gestorResiduos.registrarResiduo(solicitud.getResiduo());
            solicitud.setEstado(EstadoSolicitud.ACEPTADA);
            mensaje = "Solicitud aceptada y residuo registrado.";
        } 
        else 
        {
            solicitud.setEstado(EstadoSolicitud.RECHAZADA);
            mensaje = "Solicitud rechazada.";
        }

        guardarSolicitud();
        return mensaje;
    }

    /***
     * Método que retorna todas las solicitudes
     * @return lista completa de solicitudes
     */
    public List<Solicitud> getSolicitudes() 
    {return new ArrayList<>(solicitudes);}

    /***
     * Método flag para visualizar comportamiento de las solicitudes
     */
    public void imprimirSolicitudes() 
    {
        System.out.println("Estado actual de todas las solicitudes:");
        for (Solicitud solicitud : solicitudes) {System.out.println(solicitud);}
    }
    
}