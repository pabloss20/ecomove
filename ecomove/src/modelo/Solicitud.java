package modelo;

import java.io.Serializable;

public class Solicitud implements Serializable
{

    private static final long serialVersionUID = 1L;

    private EstadoSolicitud estado;
    private final String remitente;
    private final String correo;
    private final Residuo residuo;

    public Solicitud(String nombre, String correo, Residuo residuo)
    {
        this.remitente = nombre;
        this.correo = correo;
        this.residuo = residuo;
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public String getRemitenteNombre() {return remitente;}

    public String getRemitenteCorreo() {return correo;}

    public Residuo getResiduo() {return residuo;}

    public EstadoSolicitud getEstado() {return estado;}

    public void setEstado(EstadoSolicitud estado) {this.estado = estado;}

    @Override
    public String toString() 
    { return String.format("Solicitud de %s (%s) - Estado: %s - Residuo: %s", remitente, correo, estado, residuo);}
}