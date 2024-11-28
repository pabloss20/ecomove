package modelo;

import java.io.Serializable;

public class Premio implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    private int costo; // Costo en puntos
    private EstadoPremio estado; // Estado del premio (en tienda o no disponible)
    private String ruta; 
    
    // Constructor
    public Premio(int costo, EstadoPremio estado, String ruta) 
    {
        this.costo = costo;
        this.estado = estado;
        this.ruta = ruta;
    }
    
    public int getCosto() {return costo;}
    public void setCosto(int costo) {this.costo = costo;}
    public EstadoPremio getEstado() {return estado;}
    public void setEstado(EstadoPremio estado) {this.estado = estado;}
    public String getRuta() {return ruta;}
    public void setRuta(String ruta) {this.ruta = ruta;}

    @Override
    public String toString() 
    {return "Premio{costo: " + costo + ", estado: " + estado + ", ruta: '" + ruta + "'}";}
}
